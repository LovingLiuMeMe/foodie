package cn.lovingliu.pojo;

import java.io.Serializable;
import java.util.Date;

public class ItemsParam implements Serializable {
    /**
     * 商品参数id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 商品外键id
     *
     * @mbggenerated
     */
    private String itemId;

    /**
     * 产地 产地，例：中国江苏
     *
     * @mbggenerated
     */
    private String producPlace;

    /**
     * 保质期 保质期，例：180天
     *
     * @mbggenerated
     */
    private String footPeriod;

    /**
     * 品牌名 品牌名，例：三只大灰狼
     *
     * @mbggenerated
     */
    private String brand;

    /**
     * 生产厂名 生产厂名，例：大灰狼工厂
     *
     * @mbggenerated
     */
    private String factoryName;

    /**
     * 生产厂址 生产厂址，例：大灰狼生产基地
     *
     * @mbggenerated
     */
    private String factoryAddress;

    /**
     * 包装方式 包装方式，例：袋装
     *
     * @mbggenerated
     */
    private String packagingMethod;

    /**
     * 规格重量 规格重量，例：35g
     *
     * @mbggenerated
     */
    private String weight;

    /**
     * 存储方法 存储方法，例：常温5~25°
     *
     * @mbggenerated
     */
    private String storageMethod;

    /**
     * 食用方式 食用方式，例：开袋即食
     *
     * @mbggenerated
     */
    private String eatMethod;

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

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getProducPlace() {
        return producPlace;
    }

    public void setProducPlace(String producPlace) {
        this.producPlace = producPlace;
    }

    public String getFootPeriod() {
        return footPeriod;
    }

    public void setFootPeriod(String footPeriod) {
        this.footPeriod = footPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryAddress() {
        return factoryAddress;
    }

    public void setFactoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
    }

    public String getPackagingMethod() {
        return packagingMethod;
    }

    public void setPackagingMethod(String packagingMethod) {
        this.packagingMethod = packagingMethod;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStorageMethod() {
        return storageMethod;
    }

    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }

    public String getEatMethod() {
        return eatMethod;
    }

    public void setEatMethod(String eatMethod) {
        this.eatMethod = eatMethod;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemId=").append(itemId);
        sb.append(", producPlace=").append(producPlace);
        sb.append(", footPeriod=").append(footPeriod);
        sb.append(", brand=").append(brand);
        sb.append(", factoryName=").append(factoryName);
        sb.append(", factoryAddress=").append(factoryAddress);
        sb.append(", packagingMethod=").append(packagingMethod);
        sb.append(", weight=").append(weight);
        sb.append(", storageMethod=").append(storageMethod);
        sb.append(", eatMethod=").append(eatMethod);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}