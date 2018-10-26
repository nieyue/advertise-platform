package com.yayao.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 角色权限
 * @author yy
 *
 */
@ApiModel(value="角色权限",description="角色权限")
@TableName(value="role_permission_tb")
public class RolePermission implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 角色权限id
     */
    @ApiModelProperty(value="角色权限id",example="角色权限id")
    @TableId(value="role_permission_id")
    private Long rolePermissionId;

    /**
     * 范围，1公共，2自身
     */
    @ApiModelProperty(value="范围，1公共，2自身",example="范围，1公共，2自身")
    private Integer region;
    /**
     * 角色id
     */
    @ApiModelProperty(value="角色id",example="角色id")
    private Long roleId;
    /**
     * 权限id
     */
    @ApiModelProperty(value="权限id",example="权限id")
    private Long permissionId;
    /**
     * 角色权限更新时间
     */
    @ApiModelProperty(value="角色权限更新时间",example="角色权限更新时间")
    private Date updateDate;
    /**
     * 权限
     */
    @ApiModelProperty(value="权限",example="权限")
    @TableField(exist=false)
    private Permission permission;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Long rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
