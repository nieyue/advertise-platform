package com.yayao.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 * @author yy
 *
 */
@ApiModel(value="角色",description="角色")
@TableName("role_tb")
public class Role implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 角色id
     */
    @ApiModelProperty(value="角色id",example="角色id")
    @TableId("role_id")
    private Long roleId;

    /**
     * 角色名
     */
    @ApiModelProperty(value="角色名",example="角色名")
    private String name;
    /**
     * 角色职责
     */
    @ApiModelProperty(value="角色职责",example="角色职责")
    private String duty;
    /**
     * 角色更新时间
     */
    @ApiModelProperty(value="角色更新时间",example="角色更新时间")
    private Date updateDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}



