package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 阅读广告
 * @author 聂跃
 */
@ApiModel(value="阅读广告",description="阅读广告")
@TableName("read_advertise_tb")
public class ReadAdvertise implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 阅读广告id
	 */
	@ApiModelProperty(value="阅读广告id",example="阅读广告id")
	@TableId("read_advertise_id")
	private Long readAdvertiseId;
	/**
	 * 广告名称
	 */
	@ApiModelProperty(value="广告名称",example="广告名称")
	private String name;
	/**
	 * 广告形式（图文、贴片）
	 */
	@ApiModelProperty(value="广告形式（图文、贴片）",example="广告形式（图文、贴片）")
	private Integer type;
	/**
	 * 广告单价
	 */
	@ApiModelProperty(value="广告单价",example="广告单价")
	private Double unitPrice;
	/**
	 * 广告预算（总额）
	 */
	@ApiModelProperty(value="广告预算（总额）",example="广告预算（总额）")
	private Double totalPrice;
	/**
	 * 广告金额
	 */
	@ApiModelProperty(value="广告金额",example="广告金额")
	private Double consumePrice;
	/**
	 * 质检返款
	 */
	@ApiModelProperty(value="质检返款",example="质检返款")
	private Double qualityPrice;
	/**
	 * 实际消费
	 */
	@ApiModelProperty(value="实际消费",example="实际消费")
	private Double finalPrice;
	/**
	 * 引导文案
	 */
	@ApiModelProperty(value="引导文案",example="引导文案")
	private String guideContent;
	/**
	 * 广告图片（690* 420或500* 800,px）
	 */
	@ApiModelProperty(value="广告图片（690* 420或500* 800,px）",example="广告图片（690* 420或500* 800,px）")
	private String guideImg;
	/**
	 * 标题
	 */
	@ApiModelProperty(value="标题",example="标题")
	private String title;
	/**
	 * 作者
	 */
	@ApiModelProperty(value="作者",example="作者")
	private String author;
	/**
	 * 头条封面
	 */
	@ApiModelProperty(value="头条封面",example="头条封面")
	private String topImg;
	/**
	 * 非头条封面
	 */
	@ApiModelProperty(value="非头条封面",example="非头条封面")
	private String notopImg;
	/**
	 * 正文内容
	 */
	@ApiModelProperty(value="正文内容",example="正文内容")
	private String content;
	/**
	 * 原文链接
	 */
	@ApiModelProperty(value="原文链接",example="原文链接")
	private String originUrl;
	/**
	 * 状态，1待支付、2待审核、3投放中、4暂停中、5已完成
	 */
	@ApiModelProperty(value="状态，1待支付、2待审核、3投放中、4暂停中、5已完成",example="状态，1待支付、2待审核、3投放中、4暂停中、5已完成")
	private Integer status;
	/**
	 *投放开始时间
	 */
	@ApiModelProperty(value="投放开始时间",example="投放开始时间")
	private Date startDate;
	/**
	 *投放结束时间
	 */
	@ApiModelProperty(value="投放结束时间",example="投放结束时间")
	private Date endDate;
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
	 * 账户id
	 */
	@ApiModelProperty(value="账户id",example="账户id")
	private Long accountId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getReadAdvertiseId() {
		return readAdvertiseId;
	}

	public void setReadAdvertiseId(Long readAdvertiseId) {
		this.readAdvertiseId = readAdvertiseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getConsumePrice() {
		return consumePrice;
	}

	public void setConsumePrice(Double consumePrice) {
		this.consumePrice = consumePrice;
	}

	public Double getQualityPrice() {
		return qualityPrice;
	}

	public void setQualityPrice(Double qualityPrice) {
		this.qualityPrice = qualityPrice;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getGuideContent() {
		return guideContent;
	}

	public void setGuideContent(String guideContent) {
		this.guideContent = guideContent;
	}

	public String getGuideImg() {
		return guideImg;
	}

	public void setGuideImg(String guideImg) {
		this.guideImg = guideImg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTopImg() {
		return topImg;
	}

	public void setTopImg(String topImg) {
		this.topImg = topImg;
	}

	public String getNotopImg() {
		return notopImg;
	}

	public void setNotopImg(String notopImg) {
		this.notopImg = notopImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
}
