package cn.lovingliu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Carousel implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 图片 图片地址
     *
     * @mbggenerated
     */
    private String imageUrl;

    /**
     * 背景色
     *
     * @mbggenerated
     */
    private String backgroundColor;

    /**
     * 商品id 商品id
     *
     * @mbggenerated
     */
    private String itemId;

    /**
     * 商品分类id 商品分类id
     *
     * @mbggenerated
     */
    private String catId;

    /**
     * 轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 轮播图展示顺序
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * 是否展示
     *
     * @mbggenerated
     */
    private Integer isShow;

    /**
     * 创建时间 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 更新时间 更新
     *
     * @mbggenerated
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", backgroundColor=").append(backgroundColor);
        sb.append(", itemId=").append(itemId);
        sb.append(", catId=").append(catId);
        sb.append(", type=").append(type);
        sb.append(", sort=").append(sort);
        sb.append(", isShow=").append(isShow);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}