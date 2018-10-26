package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 汇总
 * @author 聂跃
 */
@Data
@ApiModel(value="汇总",description="汇总")
@TableName("collect_tb")
public class Collect implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 汇总id
	 */
	@ApiModelProperty(value="汇总id",example="汇总id")
	@TableId("collect_id")
	private Long collectId;
	/**
	 * 待发布
	 */
	@ApiModelProperty(value="待发布",example="待发布")
	private Integer waitRelease;
	/**
	 * 发布中
	 */
	@ApiModelProperty(value="发布中",example="发布中")
	private Integer released;
	/**
	 * 已完成
	 */
	@ApiModelProperty(value="已完成",example="已完成")
	private Integer completed;
	/**
	 * 已取消
	 */
	@ApiModelProperty(value="已取消",example="已取消")
	private Integer cancelled;
	/**
	 * 已结算
	 */
	@ApiModelProperty(value="已结算",example="已结算")
	private Integer settled;
	/**
	 * 覆盖粉丝数
	 */
	@ApiModelProperty(value="覆盖粉丝数",example="覆盖粉丝数")
	private Long funs;
	/**
	 * 实际阅读量
	 */
	@ApiModelProperty(value="实际阅读量",example="实际阅读量")
	private Long readingNumber;
	/**
	 * 实际消费
	 */
	@ApiModelProperty(value="实际消费",example="实际消费")
	private Double consume;
	/**
	 * 实际收入
	 */
	@ApiModelProperty(value="实际收入",example="实际收入")
	private Double income;
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

}
