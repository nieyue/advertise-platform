package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 媒体日数据
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="媒体日数据",description="媒体日数据")
@TableName("media_daily_data_tb")
@Data
public class MediaDailyData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 媒体日数据id
	 */
	@ApiModelProperty(value="媒体日数据id",example="媒体日数据id")
	@TableId("media_daily_data_id")
	private Long mediaDailyDataId;
	/**
	 * 文章发布数
	 */
	@ApiModelProperty(value="文章发布数",example="文章发布数")
	private Long articleNumber;
	/**
	 * top阅读数
	 */
	@ApiModelProperty(value="top阅读数",example="top阅读数")
	private Long topReadingNumber;
	/**
	 * 总阅读数
	 */
	@ApiModelProperty(value="总阅读数",example="总阅读数")
	private Long totalReadingNumber;
	/**
	 * 平均阅读数
	 */
	@ApiModelProperty(value="平均阅读数",example="平均阅读数")
	private Long avgReadingNumber;
	/**
	 * 点赞数
	 */
	@ApiModelProperty(value="点赞数",example="点赞数")
	private Long pointNumber;
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
	 * 媒体id外键
	 */
	@ApiModelProperty(value="媒体id外键",example="媒体id外键")
	private Long mediaId;
	/**
	 * 账户id外键
	 */
	@ApiModelProperty(value="账户id外键",example="账户id外键")
	private Long accountId;

}
