package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.Company;
import com.yayao.exception.CommonRollbackException;
import com.yayao.service.CompanyService;
import com.yayao.util.MyDom4jUtil;
import com.yayao.util.StateResultList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 公司控制类
 * @author yy
 *
 */
@Api(tags={"company"},value="公司",description="公司管理")
@RestController
@RequestMapping("/company")
public class CompanyController extends BaseController<Company,Long> {
	@Resource
	private CompanyService companyService;
	
	/**
	 * 公司分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "公司列表", notes = "公司分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="账户ID",dataType="long", paramType = "query"),
	  	@ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  	@ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
		@ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="companyId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Company>> browsePagingCompany(
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="companyId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<Company> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("account_id", accountId);
		 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<Company>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 公司修改
	 * @return
	 */
	@ApiOperation(value = "公司修改", notes = "公司修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Company>> updateCompany(
			@ModelAttribute Company company,HttpSession session)  {
		company.setUpdateDate(new Date());
		StateResultList<List<Company>> u = super.update(company);
		return u;
	}
	/**
	 * 公司增加
	 * @return 
	 */
	@ApiOperation(value = "公司增加", notes = "公司增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Company>> addCompany(
			@ModelAttribute Company company,
			HttpSession session) {
		Wrapper<Company> wrapper=new EntityWrapper<>();
		Map<String,Object> map=new HashMap<>();
		map.put("account_id", company.getAccountId());
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		List<Company> rl = companyService.list(1, 1, null, null, wrapper);
		if(rl.size()>0){
			throw new CommonRollbackException("已经存在");
		}
		company.setCreateDate(new Date());
		company.setUpdateDate(new Date());
		StateResultList<List<Company>> a = super.add(company);
		return a;
	}
	/**
	 * 公司删除
	 * @return
	 */
	@ApiOperation(value = "公司删除", notes = "公司删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="companyId",value="公司ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Company>> delCompany(
			@RequestParam("companyId") Long companyId,HttpSession session)  {
		StateResultList<List<Company>> d = super.delete(companyId);
		return d;
	}
	/**
	 * 公司浏览数量
	 * @return
	 */
	@ApiOperation(value = "公司数量", notes = "公司数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query")
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<Company> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
	 	map.put("account_id", accountId);
	 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 公司单个加载
	 * @return
	 */
	@ApiOperation(value = "公司单个加载", notes = "公司单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="companyId",value="公司ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Company>> loadCompany(@RequestParam("companyId") Long companyId,HttpSession session)  {
		 StateResultList<List<Company>> l = super.load(companyId);
		 return l;
	}
	
}
