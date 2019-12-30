package cn.lovingliu.pojo;

import java.io.Serializable;
import java.util.Date;

public class ItemsComments implements Serializable {
    /**
     * id主键
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 用户id 用户名须脱敏
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 商品id
     *
     * @mbggenerated
     */
    private String itemId;

    /**
     * 商品名称
     *
     * @mbggenerated
     */
    private String itemName;

    /**
     * 商品规格id 可为空
     *
     * @mbggenerated
     */
    private String itemSpecId;

    /**
     * 规格名称 可为空
     *
     * @mbggenerated
     */
    private String sepcName;

    /**
     * 评价等级 1：好评 2：中评 3：差评
     *
     * @mbggenerated
     */
    private Integer commentLevel;

    /**
     * 评价内容
     *
     * @mbggenerated
     */
    private String content;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getSepcName() {
        return sepcName;
    }

    public void setSepcName(String sepcName) {
        this.sepcName = sepcName;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        sb.append(", userId=").append(userId);
        sb.append(", itemId=").append(itemId);
        sb.append(", itemName=").append(itemName);
        sb.append(", itemSpecId=").append(itemSpecId);
        sb.append(", sepcName=").append(sepcName);
        sb.append(", commentLevel=").append(commentLevel);
        sb.append(", content=").append(content);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}