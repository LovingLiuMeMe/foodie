package cn.lovingliu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Items implements Serializable {
    /**
     * 商品主键id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 商品名称 商品名称
     *
     * @mbggenerated
     */
    private String itemName;

    /**
     * 分类外键id 分类id
     *
     * @mbggenerated
     */
    private Integer catId;

    /**
     * 一级分类外键id
     *
     * @mbggenerated
     */
    private Integer rootCatId;

    /**
     * 累计销售 累计销售
     *
     * @mbggenerated
     */
    private Integer sellCounts;

    /**
     * 上下架状态 上下架状态,1:上架 2:下架
     *
     * @mbggenerated
     */
    private Integer onOffStatus;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createdTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updatedTime;

    /**
     * 商品内容 商品内容
     *
     * @mbggenerated
     */
    private String content;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getRootCatId() {
        return rootCatId;
    }

    public void setRootCatId(Integer rootCatId) {
        this.rootCatId = rootCatId;
    }

    public Integer getSellCounts() {
        return sellCounts;
    }

    public void setSellCounts(Integer sellCounts) {
        this.sellCounts = sellCounts;
    }

    public Integer getOnOffStatus() {
        return onOffStatus;
    }

    public void setOnOffStatus(Integer onOffStatus) {
        this.onOffStatus = onOffStatus;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemName=").append(itemName);
        sb.append(", catId=").append(catId);
        sb.append(", rootCatId=").append(rootCatId);
        sb.append(", sellCounts=").append(sellCounts);
        sb.append(", onOffStatus=").append(onOffStatus);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}