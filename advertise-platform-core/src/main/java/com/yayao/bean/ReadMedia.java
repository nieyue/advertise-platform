package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 阅读媒体
 * @author 聂跃
 */
@ApiModel(value="阅读媒体",description="阅读媒体")
@TableName("read_media_tb")
@Data
public class ReadMedia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 阅读媒体id
	 */
	@ApiModelProperty(value="阅读媒体id",example="阅读媒体id")
	@TableId("read_media_id")
	private Long readMediaId;
	/**
	 * 媒体名称
	 */
	@ApiModelProperty(value="媒体名称",example="媒体名称")
	private String name;
	/**
	 * 粉丝数
	 */
	@ApiModelProperty(value="粉丝数",example="粉丝数")
	private Long funs;
	/**
	 * 广告位置,1头条，2二条，3三条，4四条，5五条，6六条
	 */
	@ApiModelProperty(value="广告位置,1头条，2二条，3三条，4四条，5五条，6六条",example="广告位置,1头条，2二条，3三条，4四条，5五条，6六条")
	private Integer position;
	/**
	 * 预估阅读量
	 */
	@ApiModelProperty(value="预估阅读量",example="预估阅读量")
	private Long expectReadingNumber;
	/**
	 * 实际阅读量
	 */
	@ApiModelProperty(value="实际阅读量",example="实际阅读量")
	private Long finalReadingNumber;
	/**
	 * 广告单价
	 */
	@ApiModelProperty(value="广告单价",example="广告单价")
	private Double unitPrice;
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
	 * 发布链接
	 */
	@ApiModelProperty(value="发布链接",example="发布链接")
	private String url;
	/**
	 * 状态，1待审核、2待发布、3发布中、4待结算、5已完成、6审核未通过、7已取消
	 */
	@ApiModelProperty(value="状态，1待审核、2待发布、3发布中、4待结算、5已完成、6审核未通过、7已取消",example="状态，1待审核、2待发布、3发布中、4待结算、5已完成、6审核未通过、7已取消")
	private Integer status;
	/**
	 *预计发布时间
	 */
	@ApiModelProperty(value="预计发布时间",example="预计发布时间")
	private Date expectDate;
	/**
	 *实际发布时间
	 */
	@ApiModelProperty(value="实际发布时间",example="实际发布时间")
	private Date finalDate;
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
	 * 媒体id
	 */
	@ApiModelProperty(value="媒体id",example="媒体id")
	private Long mediaId;
	/**
	 * 阅读媒体id
	 */
	@ApiModelProperty(value="阅读媒体id",example="阅读媒体id")
	private Long readAdvertiseId;
	/**
	 * 账户id
	 */
	@ApiModelProperty(value="账户id",example="账户id")
	private Long accountId;

}
