package com.yayao.business;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.Account;
import com.yayao.bean.Role;
import com.yayao.bean.RolePermission;
import com.yayao.exception.*;
import com.yayao.service.AccountService;
import com.yayao.service.RolePermissionService;
import com.yayao.service.RoleService;
import com.yayao.util.MyDESutil;
import com.yayao.util.MyDom4jUtil;
import com.yayao.util.MyValidator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Pattern;
/**
 * 账户业务逻辑
 * @author 聂跃
 * @date 2017年8月19日
 */
@Configuration
public class AccountBusiness {
	@Resource
	AccountService accountService;
    @Autowired
    private RoleService roleService;

	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Value("${myPugin.projectName}")
	String projectName;
    /**
     * 存储账户会话信息
     */
    public List<Map<String,Object>> accountSession(
    		Account account
    		){
    	
    	Session session=SecurityUtils.getSubject().getSession();
    	List<Map<String,Object>> list = new ArrayList<>();
	        //将用户信息放入session中
			session.setAttribute("account", account);
			//角色
	        Long roleId = account.getRoleId();
			Role r = roleService.load(roleId);
			session.setAttribute("role", r);
			//权限放入session
			Wrapper<RolePermission> wrapper=new EntityWrapper<>();
		 	Map<String,Object> mw=new HashMap<>();
		 	mw.put("role_id",  account.getRoleId());
		 	wrapper.allEq(MyDom4jUtil.getNoNullMap(mw));
			List<RolePermission> rolePermissionList = rolePermissionService.list(1, Integer.MAX_VALUE, null, null, wrapper);
	        session.setAttribute("rolePermissionList", rolePermissionList);
	        //map放入session
	        Map<String,Object> map=new HashMap<>();
	        //账户
			map.put("account", account);
			//自身角色
			map.put("role", r);
			//自身权限
			map.put("rolePermissionList", rolePermissionList);
			session.setAttribute("loginMap", map);//可以放入session
			list.add(map);
			return list;
    	
    }
	/**
	 * 登录通用
	 */
	public List<Map<String,Object>> loginCommon(
			String adminName,
			String password
			){
		List<Map<String,Object>> list = new ArrayList<>();
		 Account account = accountService.loginAccount(adminName, password, null);
	    	//自动登陆
//			if(account==null|| account.equals("")){
//				account=accountService.loginAccount(adminName, password, null);
//			}
	        if (ObjectUtils.isEmpty(account)) {
				throw new AccountLoginException();//账户或密码错误
			}
		if(account.getStatus().equals(1)){
			throw new AccountLockException();//账户封禁
			}
	        //更新登陆时间
	        account.setLoginDate(new Date());
			boolean b = accountService.update(account);
			if(b){
				list=accountSession(account);
			}
			return list;
	}
	/**
	 * web登录
	 * @return false 不包含，  true 包含
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> webLogin(
			String adminName,
			String password,
			Integer roleType,
			String verificationCode,
			HttpSession session
			){
		//1代验证码
		String ran= (String) session.getAttribute("verificationCode");
		if(ran==null||!ran.equals(verificationCode)){
			throw new VerifyCodeErrorException();
		}

		//角色类型
		Account account = accountService.loginAccount(adminName, password, null);
		if(ObjectUtils.isEmpty(account)){
			throw new AccountLoginException();
		}
		List<Role> roleList = roleService.list(1, Integer.MAX_VALUE, "role_id","asc",null);
		boolean isrole=false;//是否对应的角色，默认false
		for (Role role : roleList) {
			if(role!=null  ){
				//角色为广告主且角色相同，
				if(roleType.equals(1) && role.getName().equals("广告主")&&role.getRoleId().equals(account.getRoleId()) ){
					isrole=true;
				}else if(roleType.equals(2) && role.getName().equals("媒体主")&& role.getRoleId().equals(account.getRoleId())){
					// 角色为渠道主且角色相同
					isrole=true;

				}
			}
		}
		if(!isrole){
			throw new AccountLoginException();
		}
		List<Map<String,Object>> list = new ArrayList<>();
		 Subject currentUser = SecurityUtils.getSubject();
		 UsernamePasswordToken token = new UsernamePasswordToken(adminName, password);
		 //UsernamePasswordToken token = new UsernamePasswordToken(adminName, MyDESutil.getMD5(password));
		 try {
	            currentUser.login(token);
	        } catch (AuthenticationException e) {
	            throw new AccountLoginException();//
	        }
		 Map<String,Object> loginMap=(Map<String, Object>) session.getAttribute("loginMap");
		 list.add(loginMap);
		 session.removeAttribute("loginMap");//去掉
		return list;
	}
	/**
	 * 管理员登录
	 * @return false 不包含，  true 包含
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> login(
			String adminName,
			String password,
			String verificationCode,
			HttpSession session
			){
		//1代验证码
		String ran= (String) session.getAttribute("verificationCode");
		if(ran==null||!ran.equals(verificationCode)){
			throw new VerifyCodeErrorException();//验证码
		}
		List<Map<String,Object>> list = new ArrayList<>();
		Subject currentUser = SecurityUtils.getSubject();
		 UsernamePasswordToken token = new UsernamePasswordToken(adminName, password);
		 //UsernamePasswordToken token = new UsernamePasswordToken(adminName, MyDESutil.getMD5(password));
		 try {
	            currentUser.login(token);
	        } catch (AuthenticationException e) {
	            throw new AccountLoginException();//
	        }
		 Role r = (Role) session.getAttribute("role");
		 if(!ObjectUtils.isEmpty(r)){
			 if(r.getName().indexOf("管理员")<0){
				 session.invalidate();//不能登录
				 throw new MySessionException();
			 }
		 }
		 Map<String,Object> loginMap=(Map<String, Object>) session.getAttribute("loginMap");
		 list.add(loginMap);
		 session.removeAttribute("loginMap");//去掉
		 return list;
		}
	/**
	 * 账户注册
	 * roleType 角色类型，1商户，2推广户，3用户
	 * nickname 用户名称
	 * email 邮箱号
	 * phone 手机号
	 * password 密码
	 * validCode 验证码
	 */
	public List<Map<String,Object>> webRegister(
			Integer roleType,
			String adminName,
			String verificationCode,
			String password,
			String validCode,
			HttpSession session
	){
		List<Map<String,Object>> list = new ArrayList<>();
		if(adminName==null){
			throw new CommonRollbackException("缺失账号");//异常
		}
		//1代图片验证码
		String ran= (String) session.getAttribute("verificationCode");
		if(ran==null||!ran.equals(verificationCode)){
			throw new VerifyCodeErrorException();//验证码
		}
		//手机/邮箱验证码
		if(adminName!=null){
			String vc = (String) session.getAttribute("validCode");
			if(validCode==null||!validCode.equals(vc)){
				throw new VerifyCodeErrorException();//验证码错误
			}
		}
		if(!Pattern.matches(MyValidator.REGEX_PHONE,adminName)
				&&!Pattern.matches(MyValidator.REGEX_EMAIL,adminName)){
			throw new CommonRollbackException("手机或者邮箱错误");
		}
		//判断是否存在
		Account ac = accountService.loginAccount(adminName, null, null);
		if(ac!=null&&ac.getAccountId()!=null){
			throw new AccountIsExistException();//账户已经存在
		}
		//新用户注册登录
		Account account=new Account();
		if(Pattern.matches(MyValidator.REGEX_PHONE,adminName)){
			account.setPhone(adminName);
		}else if(Pattern.matches(MyValidator.REGEX_EMAIL,adminName)){
			account.setEmail(adminName);
		}
		account.setPassword(MyDESutil.getMD5(password));
		List<Role> roleList = roleService.list(1, Integer.MAX_VALUE, "role_id","asc",null);
		for (Role role : roleList) {
			if(role!=null  ){
				if( roleType.equals(1) && role.getName().equals("广告主") ){
					account.setRoleId(role.getRoleId());
				}else if( roleType.equals(2) && role.getName().equals("媒体主") ){
					account.setRoleId(role.getRoleId());
				}
			}
		}
		account.setCreateDate(new Date());
		account.setLoginDate(new Date());
		account.setAuth(0);//未认证
		account.setSex(0);//未知
		account.setSafetyGrade(1);//安全等级低
		boolean b = accountService.add(account);
		if(b){
			list=accountSession(account);
		}
		return list;

	}
}
