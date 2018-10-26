package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 媒体详细数据
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="媒体详细数据",description="媒体详细数据")
@TableName("media_data_tb")
@Data
public class MediaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 媒体详细数据id
	 */
	@ApiModelProperty(value="媒体详细数据id",example="媒体详细数据id")
	@TableId("media_data_id")
	private Long mediaDataId;
	/**
	 * 文章标题
	 */
	@ApiModelProperty(value="文章标题",example="文章标题")
	private String name;
	/**
	 * 文章位置,1头条，2二条，3三条，4四条，5五条，6六条
	 */
	@ApiModelProperty(value="文章位置,1头条，2二条，3三条，4四条，5五条，6六条",example="文章位置,1头条，2二条，3三条，4四条，5五条，6六条")
	private Integer position;
	/**
	 * 阅读数
	 */
	@ApiModelProperty(value="阅读数",example="阅读数")
	private Long readingNumber;
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
