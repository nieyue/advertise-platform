package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
/**
 * 财务记录
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="财务记录",description="财务记录")
@TableName("finance_record_tb")
public class FinanceRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 财务记录id
	 */
	@ApiModelProperty(value="财务记录id",example="财务记录id")
	@TableId("finance_record_id")
	private Long financeRecordId;
	/**
	 * 方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额
	 */
	@ApiModelProperty(value="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额",example="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额")
	private Integer method;
	/**
	 * 类型，1充值、2消费，3退款，4冻结，5解冻，6收入，7提现
	 */
	@ApiModelProperty(value="类型，1充值、2消费，3退款，4冻结，5解冻，6收入，7提现",example="类型，1充值、2消费，3退款，4冻结，5解冻，6收入，7提现")
	private Integer type;
	/**
	 * 交易单号
	 */
	@ApiModelProperty(value="交易单号",example="交易单号")
	private String transactionNumber;
	/**
	 * 广告名称
	 */
	@ApiModelProperty(value="广告名称",example="广告名称")
	private String advertiseName;
	/**
	 * 媒体名称
	 */
	@ApiModelProperty(value="媒体名称",example="媒体名称")
	private String mediaName;
	/**
	 * 金额
	 */
	@ApiModelProperty(value="金额",example="金额")
	private Double money;
	/**
	 * 税金
	 */
	@ApiModelProperty(value="税金",example="税金")
	private Double tax;
	/**
	 * 平台服务费
	 */
	@ApiModelProperty(value="平台服务费",example="平台服务费")
	private Double brokerage;
	/**
	 * 实际金额
	 */
	@ApiModelProperty(value="实际金额",example="实际金额")
	private Double realMoney;
	/**
	 * 状态，默认1待处理，2成功，3已拒绝
	 */
	@ApiModelProperty(value="状态，默认1待处理，2成功，3已拒绝",example="状态，默认1待处理，2成功，3已拒绝")
	private Integer status;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	/**
	 * 广告id,外键
	 */
	@ApiModelProperty(value="广告id,外键",example="广告id,外键")
	private Long advertiseId;
	/**
	 * 账户id外键
	 */
	@ApiModelProperty(value="账户id外键",example="账户id外键")
	private Long accountId;
	public Long getFinanceRecordId() {
		return financeRecordId;
	}
	public void setFinanceRecordId(Long financeRecordId) {
		this.financeRecordId = financeRecordId;
	}
	public Integer getMethod() {
		return method;
	}
	public void setMethod(Integer method) {
		this.method = method;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public Double getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(Double brokerage) {
		this.brokerage = brokerage;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getRealMoney() {
		return realMoney;
	}
	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAdvertiseName() {
		return advertiseName;
	}

	public void setAdvertiseName(String advertiseName) {
		this.advertiseName = advertiseName;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Long getAdvertiseId() {
		return advertiseId;
	}

	public void setAdvertiseId(Long advertiseId) {
		this.advertiseId = advertiseId;
	}
}
