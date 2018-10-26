package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 媒体
 * @author 聂跃
 */
@Data
@ApiModel(value="媒体",description="媒体")
@TableName("media_tb")
public class Media implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 媒体id
	 */
	@ApiModelProperty(value="媒体id",example="媒体id")
	@TableId("media_id")
	private Long mediaId;
	/**
	 * 媒体名称
	 */
	@ApiModelProperty(value="媒体名称",example="媒体名称")
	private String name;
	/**
	 * 媒体账号
	 */
	@ApiModelProperty(value="媒体账号",example="媒体账号")
	private String accountName;
	/**
	 * 微信头像
	 */
	@ApiModelProperty(value="微信头像",example="微信头像")
	private String wechatImg;
	/**
	 * 二维码
	 */
	@ApiModelProperty(value="二维码",example="二维码")
	private String qrCode;
	/**
	 * 粉丝数
	 */
	@ApiModelProperty(value="粉丝数",example="粉丝数")
	private Long funs;
	/**
	 * 粉丝截图
	 */
	@ApiModelProperty(value="粉丝截图",example="粉丝截图")
	private String funsImg;
	/**
	 * 用户属性
	 */
	@ApiModelProperty(value="用户属性",example="用户属性")
	private String userImg;
	/**
	 * 阅读量
	 */
	@ApiModelProperty(value="阅读量",example="阅读量")
	private Long readingNumber;
	/**
	 * 性别男比例,单位%
	 */
	@ApiModelProperty(value="性别男比例,单位%",example="性别男比例,单位%")
	private Double sexManScale;
	/**
	 * 性别女比例,单位%
	 */
	@ApiModelProperty(value="性别女比例,单位%",example="性别女比例,单位%")
	private Double sexWomanScale;
	/**
	 * 粉丝top1地址
	 */
	@ApiModelProperty(value="粉丝top1地址",example="粉丝top1地址")
	private String funsTop1Address;
	/**
	 * 粉丝top2地址
	 */
	@ApiModelProperty(value="粉丝top2地址",example="粉丝top2地址")
	private String funsTop2Address;
	/**
	 * 粉丝top3地址
	 */
	@ApiModelProperty(value="粉丝top3地址",example="粉丝top3地址")
	private String funsTop3Address;
	/**
	 * 授权,1未授权，2已授权
	 */
	@ApiModelProperty(value="授权,1未授权，2已授权",example="授权,1未授权，2已授权")
	private Integer auth;
	/**
	 * 是否接单,1是，2否
	 */
	@ApiModelProperty(value="是否接单,1是，2否",example="是否接单,1是，2否")
	private Integer takeOrder;
	/**
	 * 多图文头条报价
	 */
	@ApiModelProperty(value="多图文头条报价",example="多图文头条报价")
	private Double top1Price;
	/**
	 * 多图文二条报价
	 */
	@ApiModelProperty(value="多图文二条报价",example="多图文二条报价")
	private Double top2Price;
	/**
	 * 多图文3-N条报价
	 */
	@ApiModelProperty(value="多图文3-N条报价",example="多图文3-N条报价")
	private Double top3nPrice;
	/**
	 * 单图文报价
	 */
	@ApiModelProperty(value="单图文报价",example="单图文报价")
	private Double singlePrice;
	/**
	 * 状态，1审核中、2正常、3审核未通过
	 */
	@ApiModelProperty(value="状态，1审核中、2正常、3审核未通过",example="状态，1审核中、2正常、3审核未通过")
	private Integer status;
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
	 * 媒体类型id
	 */
	@ApiModelProperty(value="媒体类型id",example="媒体类型id")
	private Long mediaCateId;
	/**
	 * 账户id
	 */
	@ApiModelProperty(value="账户id",example="账户id")
	private Long accountId;


}
