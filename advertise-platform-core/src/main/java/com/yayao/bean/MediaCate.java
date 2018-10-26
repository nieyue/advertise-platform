package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 媒体类型
 * @author yy
 *
 */
@ApiModel(value="媒体类型",description="媒体类型")
@TableName("media_cate_tb")
public class MediaCate implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 媒体类型id
     */
    @ApiModelProperty(value="媒体类型id",example="媒体类型id")
    @TableId("media_cate_id")
    private Long mediaCateId;

    /**
     * 媒体类型名
     */
    @ApiModelProperty(value="媒体类型名",example="媒体类型名")
    private String name;
    /**
     * 媒体类型职责
     */
    @ApiModelProperty(value="媒体类型职责",example="媒体类型职责")
    private String summary;
    /**
     * 媒体类型更新时间
     */
    @ApiModelProperty(value="媒体类型更新时间",example="媒体类型更新时间")
    private Date updateDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMediaCateId() {
        return mediaCateId;
    }

    public void setMediaCateId(Long mediaCateId) {
        this.mediaCateId = mediaCateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}



