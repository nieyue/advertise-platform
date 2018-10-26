package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 媒体收藏
 * @author 聂跃
 */
@Data
@ApiModel(value="媒体收藏",description="媒体收藏")
@TableName("media_collection_tb")
public class MediaCollection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 媒体收藏id
	 */
	@ApiModelProperty(value="媒体收藏id",example="媒体收藏id")
	@TableId("media_collection_id")
	private Long mediaCollectionId;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	/**
	 * 媒体收藏类型id
	 */
	@ApiModelProperty(value="媒体id",example="媒体id")
	private Long mediaId;
	/**
	 * 账户id
	 */
	@ApiModelProperty(value="账户id",example="账户id")
	private Long accountId;


}
