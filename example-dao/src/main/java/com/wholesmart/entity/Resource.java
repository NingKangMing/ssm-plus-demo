package com.wholesmart.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-09-08
 */
@ApiModel(value="资源表")
@TableName("t_resource")
public class Resource extends Model<Resource> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源表id
     */
    @ApiModelProperty(value="资源表id",name="id")
	private Long id;
    /**
     * 资源父级id
     */
    @ApiModelProperty(value="资源父级id",name="parentId")
	@TableField("parent_id")
	private Long parentId;
    /**
     * 资源编码
     */
    @ApiModelProperty(value="资源编码",name="sourceCode")
	@TableField("source_code")
	private String sourceCode;
    /**
     * 资源名称
     */
    @ApiModelProperty(value="资源名称",name="sourceName")
	@TableField("source_name")
	private String sourceName;
    /**
     * 资源标识
     */
    @ApiModelProperty(value="资源标识",name="sourceKey")
	@TableField("source_key")
	private String sourceKey;
    /**
     * 资源类型,1:菜单;2:按钮
     */
    @ApiModelProperty(value="资源类型,1:菜单;2:按钮",name="type")
	private Integer type;
    /**
     * 资源url
     */
    @ApiModelProperty(value="资源url",name="url")
	private String url;
    /**
     * 小图标路径
     */
    @ApiModelProperty(value="小图标路径",name="icon")
	private String icon;
    /**
     * 是否隐藏
     */
    @ApiModelProperty(value="是否隐藏",name="hidden")
	private Integer hidden;
    /**
     * 资源描述
     */
    @ApiModelProperty(value="资源描述",name="description")
	private String description;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime")
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",name="updateTime")
	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	private Date updateTime;
    /**
     * 树深度
     */
    @ApiModelProperty(value="树深度",name="level")
	private Integer level;
    
    @TableField(exist=false)
   	private String parentName;
   	
   	@TableField(exist=false)
   	private boolean checked;
   	
   	@TableField(exist=false)
   	private boolean last;
   	
   	@TableField(exist=false)
   	private List<Resource> children = new ArrayList<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceKey() {
		return sourceKey;
	}

	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getHidden() {
		return hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Resource{" +
			"id=" + id +
			", parentId=" + parentId +
			", sourceCode=" + sourceCode +
			", sourceName=" + sourceName +
			", sourceKey=" + sourceKey +
			", type=" + type +
			", url=" + url +
			", icon=" + icon +
			", hidden=" + hidden +
			", description=" + description +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", level=" + level +
			"}";
	}
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}
}
