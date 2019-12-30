package cn.lovingliu.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserAddress implements Serializable {
    /**
     * 地址主键id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 关联用户id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 收件人姓名
     *
     * @mbggenerated
     */
    private String receiver;

    /**
     * 收件人手机号
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * 省份
     *
     * @mbggenerated
     */
    private String province;

    /**
     * 城市
     *
     * @mbggenerated
     */
    private String city;

    /**
     * 区县
     *
     * @mbggenerated
     */
    private String district;

    /**
     * 详细地址
     *
     * @mbggenerated
     */
    private String detail;

    /**
     * 扩展字段
     *
     * @mbggenerated
     */
    private String extand;

    /**
     * 是否默认地址
     *
     * @mbggenerated
     */
    private Integer isDefault;

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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getExtand() {
        return extand;
    }

    public void setExtand(String extand) {
        this.extand = extand;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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
        sb.append(", receiver=").append(receiver);
        sb.append(", mobile=").append(mobile);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", district=").append(district);
        sb.append(", detail=").append(detail);
        sb.append(", extand=").append(extand);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}