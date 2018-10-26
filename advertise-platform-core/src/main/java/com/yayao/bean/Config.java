package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 配置
 * @author yy
 *
 */
@ApiModel(value="配置",description="配置")
@TableName("config_tb")
public class Config implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 配置id
	 */
	@ApiModelProperty(value="配置id",example="配置id")
	@TableId("config_id")
	private Long configId;
	/**
	 * 平台开户名称
	 */
	@ApiModelProperty(value="平台开户名称",example="平台开户名称")
	private String platformName;
	/**
	 * 平台开户银行
	 */
	@ApiModelProperty(value="平台开户银行",example="平台开户银行")
	private String bankName;
	/**
	 * 平台银行账户
	 */
	@ApiModelProperty(value="平台银行账户",example="平台银行账户")
	private String bankAccount;
	/**
	 * 平台联系电话
	 */
	@ApiModelProperty(value="平台联系电话",example="平台联系电话")
	private String servicePhone;
	/**
	 * 平台联系qq
	 */
	@ApiModelProperty(value="平台联系qq",example="平台联系qq")
	private String serviceQq;
	/**
	 * 平台服务费率，单位%
	 */
	@ApiModelProperty(value="平台服务费率，单位%",example="平台服务费率，单位%")
	private Double serviceProportion;
	/**
	 * 税率，单位%
	 */
	@ApiModelProperty(value="税率，单位%",example="税率，单位%")
	private Double taxRate;
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
	public Long getConfigId() {
		return configId;
	}
	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getServiceQq() {
		return serviceQq;
	}

	public void setServiceQq(String serviceQq) {
		this.serviceQq = serviceQq;
	}

	public Double getServiceProportion() {
		return serviceProportion;
	}

	public void setServiceProportion(Double serviceProportion) {
		this.serviceProportion = serviceProportion;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
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
}
