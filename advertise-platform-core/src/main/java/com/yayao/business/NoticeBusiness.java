package com.yayao.business;

import com.yayao.bean.Notice;
import com.yayao.exception.NoticeException;
import com.yayao.service.AccountService;
import com.yayao.service.NoticeService;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
/**
 * 通知业务
 * @author 聂跃
 * @date 2017年8月19日
 * 
 */
@Configuration
public class NoticeBusiness {
	@Resource
	AccountService accountService;
	@Resource
	NoticeService noticeService;

	/**
	 *  获取状态
	 *  @param type 类型，1系统消息，2充值，3退款，4提现
	 *  @return status 状态，默认为0正常，1审核中，2申请成功，3申请失败
	 */
	public Integer getStatusByType(Integer type){
		Integer status=0;//默认为0
		if(type==2||type==3||type==4){
			status=1;//是审核中
		}
		return status;
	}
	/**
	 *  获取标题
	 *  @param type 类型，1系统消息，2充值，3退款，4提现
	 *  @return title 系统消息，充值，退款，提现
	 */
	public String getTitleByType(Integer type){
		String title="";//默认为空
		if(type==1){
			title="系统消息";
		}else if(type==2){
			title="充值";
		}else if(type==3){
			title="退款";
		}else if(type==4){
			title="提现";
		}
		return title;
	}
	/**
	 *  通知
	 *  content 内容
	 *  类型，1系统消息，2充值，3退款，4提现
	 *  @return content
	 */
	public String getContentByType(
			Notice notice ){
		StringBuffer content = new StringBuffer();
			switch (notice.getType()) {
			//系统消息
			case 1:
				content.append(notice.getContent());
				break;
			//充值
			case 2:
				if(ObjectUtils.isEmpty(notice.getAccountId())
						){
					throw new NoticeException("充值异常");
				}
				content.append(notice.getContent());
				break;
			//退款
			case 3:
				if(ObjectUtils.isEmpty(notice.getAccountId())
				){
					throw new NoticeException("退款异常");
				}
				content.append(notice.getContent());
				break;
			//提现
			case 4:
				if(ObjectUtils.isEmpty(notice.getAccountId())
				){
					throw new NoticeException("提现异常");
				}
				content.append(notice.getContent());
				break;
			default:
				break;
			}
			
			
			return content.toString();
	}
public static void main(String[] args) {
	StringBuffer content=new StringBuffer();
	switch (2) {
	//系统消息
	case 1:
		content.append("dfs");
		break;
	case 2:
		content.append("22222");
		break;
	case 3:
		content.append("3333");
		break;
	default:
		content.append("5555");
		break;
	}
	System.out.println(content.toString());
	throw new NoticeException("sdfd");
}
}
