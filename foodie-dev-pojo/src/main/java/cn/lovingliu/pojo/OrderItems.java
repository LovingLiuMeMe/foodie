package cn.lovingliu.pojo;

import java.io.Serializable;

public class OrderItems implements Serializable {
    /**
     * 主键id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 归属订单id
     *
     * @mbggenerated
     */
    private String orderId;

    /**
     * 商品id
     *
     * @mbggenerated
     */
    private String itemId;

    /**
     * 商品图片
     *
     * @mbggenerated
     */
    private String itemImg;

    /**
     * 商品名称
     *
     * @mbggenerated
     */
    private String itemName;

    /**
     * 规格id
     *
     * @mbggenerated
     */
    private String itemSpecId;

    /**
     * 规格名称
     *
     * @mbggenerated
     */
    private String itemSpecName;

    /**
     * 成交价格
     *
     * @mbggenerated
     */
    private Integer price;

    /**
     * 购买数量
     *
     * @mbggenerated
     */
    private Integer buyCounts;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpecId() {
        return itemSpecId;
    }

    public void setItemSpecId(String itemSpecId) {
        this.itemSpecId = itemSpecId;
    }

    public String getItemSpecName() {
        return itemSpecName;
    }

    public void setItemSpecName(String itemSpecName) {
        this.itemSpecName = itemSpecName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getBuyCounts() {
        return buyCounts;
    }

    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", itemId=").append(itemId);
        sb.append(", itemImg=").append(itemImg);
        sb.append(", itemName=").append(itemName);
        sb.append(", itemSpecId=").append(itemSpecId);
        sb.append(", itemSpecName=").append(itemSpecName);
        sb.append(", price=").append(price);
        sb.append(", buyCounts=").append(buyCounts);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}