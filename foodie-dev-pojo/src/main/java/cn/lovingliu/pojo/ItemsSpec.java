package cn.lovingliu.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ItemsSpec implements Serializable {
    /**
     * 商品规格id
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
     * 规格名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 库存
     *
     * @mbggenerated
     */
    private Integer stock;

    /**
     * 折扣力度
     *
     * @mbggenerated
     */
    private BigDecimal discounts;

    /**
     * 优惠价
     *
     * @mbggenerated
     */
    private Integer priceDiscount;

    /**
     * 原价
     *
     * @mbggenerated
     */
    private Integer priceNormal;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getDiscounts() {
        return discounts;
    }

    public void setDiscounts(BigDecimal discounts) {
        this.discounts = discounts;
    }

    public Integer getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public Integer getPriceNormal() {
        return priceNormal;
    }

    public void setPriceNormal(Integer priceNormal) {
        this.priceNormal = priceNormal;
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
        sb.append(", name=").append(name);
        sb.append(", stock=").append(stock);
        sb.append(", discounts=").append(discounts);
        sb.append(", priceDiscount=").append(priceDiscount);
        sb.append(", priceNormal=").append(priceNormal);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}