package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 位置媒体
 * @author 聂跃
 */
@ApiModel(value="位置媒体",description="位置媒体")
@TableName("position_media_tb")
@Data
public class PositionMedia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 位置媒体id
	 */
	@ApiModelProperty(value="位置媒体id",example="位置媒体id")
	@TableId("position_media_id")
	private Long positionMediaId;
	/**
	 * 媒体名称
	 */
	@ApiModelProperty(value="媒体名称",example="媒体名称")
	private String mediaName;
	/**
	 * 粉丝数
	 */
	@ApiModelProperty(value="粉丝数",example="粉丝数")
	private Long funs;
	/**
	 * 广告名称
	 */
	@ApiModelProperty(value="广告名称",example="广告名称")
	private String advertiseName;
	/**
	 * 广告位置,1头条，2二条，3三条，4四条，5五条，6六条
	 */
	@ApiModelProperty(value="广告位置,1头条，2二条，3三条，4四条，5五条，6六条",example="广告位置,1头条，2二条，3三条，4四条，5五条，6六条")
	private Integer position;
	/**
	 * 广告金额
	 */
	@ApiModelProperty(value="广告金额",example="广告金额")
	private Long consumePrice;
	/**
	 * 质检扣款
	 */
	@ApiModelProperty(value="质检扣款",example="质检扣款")
	private Double qualityPrice;
	/**
	 * 广告收入
	 */
	@ApiModelProperty(value="广告收入",example="广告收入")
	private Double finalPrice;
	/**
	 * 发布链接
	 */
	@ApiModelProperty(value="发布链接",example="发布链接")
	private String url;
	/**
	 * 状态，1待接单，2已接单，3已拒单，4执行中，5已完成，6已取消
	 */
	@ApiModelProperty(value="状态，1待接单，2已接单，3已拒单，4执行中，5已完成，6已取消",example="状态，1待接单，2已接单，3已拒单，4执行中，5已完成，6已取消")
	private Integer status;
	/**
	 * 支付状态，1待支付，2已支付
	 */
	@ApiModelProperty(value="支付状态，1待支付，2已支付",example="支付状态，1待支付，2已支付")
	private Integer payStatus;
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
	 * 位置广告id
	 */
	@ApiModelProperty(value="位置广告id",example="位置广告id")
	private Long positionAdvertiseId;
	/**
	 * 账户id
	 */
	@ApiModelProperty(value="账户id",example="账户id")
	private Long accountId;

}
