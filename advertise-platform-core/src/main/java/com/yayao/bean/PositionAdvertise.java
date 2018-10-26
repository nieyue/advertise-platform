package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 位置广告
 * @author 聂跃
 */
@ApiModel(value="位置广告",description="位置广告")
@TableName("position_advertise_tb")
public class PositionAdvertise implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 位置广告id
	 */
	@ApiModelProperty(value="位置广告id",example="位置广告id")
	@TableId("position_advertise_id")
	private Long positionAdvertiseId;
	/**
	 * 广告名称
	 */
	@ApiModelProperty(value="广告名称",example="广告名称")
	private String name;
	/**
	 * 广告位置,1多图文头条，2多图文二条，3多图文3-N条，4单图文
	 */
	@ApiModelProperty(value="广告位置,1多图文头条，2多图文二条，3多图文3-N条，4单图文",example="广告位置,1多图文头条，2多图文二条，3多图文3-N条，4单图文")
	private Integer position;
	/**
	 * 广告金额
	 */
	@ApiModelProperty(value="广告金额",example="广告金额")
	private Double consumePrice;
	/**
	 * 实际金额
	 */
	@ApiModelProperty(value="实际金额",example="实际金额")
	private Double finalPrice;
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
	 * 封面
	 */
	@ApiModelProperty(value="封面",example="封面")
	private String topImg;
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
	 * 状态，1投放中，2已完成
	 */
	@ApiModelProperty(value="状态，1待支付、2待审核、3投放中、4暂停中、5已完成",example="状态，1待支付、2待审核、3投放中、4暂停中、5已完成")
	private Integer status;
	/**
	 *投放开始时间
	 */
	@ApiModelProperty(value="投放开始时间",example="投放开始时间")
	private Date startDate;
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

	public Long getPositionAdvertiseId() {
		return positionAdvertiseId;
	}

	public void setPositionAdvertiseId(Long positionAdvertiseId) {
		this.positionAdvertiseId = positionAdvertiseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Double getConsumePrice() {
		return consumePrice;
	}

	public void setConsumePrice(Double consumePrice) {
		this.consumePrice = consumePrice;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
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
