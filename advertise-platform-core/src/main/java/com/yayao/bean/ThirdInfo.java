package com.yayao.bean;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 第三方信息
 * @author yy
 *
 */
@ApiModel(value="第三方信息",description="第三方信息")
@TableName("third_info_tb")
public class ThirdInfo implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 第三方信息id
     */
    @ApiModelProperty(value="第三方信息id",example="第三方信息id")
    @TableId("third_info_id")
    private Long thirdInfoId;

    /**
     * 微信openid，多个，格式[{subscriptionId:'1004908098840150018',openid:'sdfsdfdsf2fd'}]
     */
    @ApiModelProperty(value="微信openid，多个，格式[{subscriptionId:'1004908098840150018',openid:'sdfsdfdsf2fd'}]",example="微信openid，多个，格式[{subscriptionId:'1004908098840150018',openid:'sdfsdfdsf2fd'}]")
    private String wxOpenid;
    /**
     * 微信uuid
     */
    @ApiModelProperty(value="微信uuid",example="微信uuid")
    private String wxUuid;
    /**
     * 微信号
     */
    @ApiModelProperty(value="微信号",example="微信号")
    private String wechat;
    /**
     *qq号
     */
    @ApiModelProperty(value="qq号",example="qq号")
    private String qq;
    /**
     *微博号
     */
    @ApiModelProperty(value="微博号",example="微博号")
    private String microblog;
    /**
     *支付宝号
     */
    @ApiModelProperty(value="支付宝号",example="支付宝号")
    private String alipay;
    /**
     *创建时间
     */
    @ApiModelProperty(value="创建时间",example="创建时间")
    private Date createDate;
    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",example="更新时间")
    private Date updateDate;
    /**
     * 账户id外键
     */
    @ApiModelProperty(value="账户id外键",example="账户id外键")
    private Long accountId;
	public Long getThirdInfoId() {
		return thirdInfoId;
	}
	public void setThirdInfoId(Long thirdInfoId) {
		this.thirdInfoId = thirdInfoId;
	}
	public String getWxOpenid() {
		return wxOpenid;
	}
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	public String getWxUuid() {
		return wxUuid;
	}
	public void setWxUuid(String wxUuid) {
		this.wxUuid = wxUuid;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMicroblog() {
		return microblog;
	}
	public void setMicroblog(String microblog) {
		this.microblog = microblog;
	}
	public String getAlipay() {
		return alipay;
	}
	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
