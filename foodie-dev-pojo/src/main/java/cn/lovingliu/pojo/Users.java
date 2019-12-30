package cn.lovingliu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
    /**
     * 主键id 用户id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 用户名 用户名
     *
     * @mbggenerated
     */
    private String username;

    /**
     * 密码 密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 昵称 昵称
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * 真实姓名
     *
     * @mbggenerated
     */
    private String realname;

    /**
     * 头像
     *
     * @mbggenerated
     */
    private String face;

    /**
     * 手机号 手机号
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * 邮箱地址 邮箱地址
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 性别 性别 1:男  0:女  2:保密
     *
     * @mbggenerated
     */
    private Integer sex;

    /**
     * 生日 生日
     *
     * @mbggenerated
     */
    private Date birthday;

    /**
     * 创建时间 创建时间
     *
     * @mbggenerated
     */
    private Date createdTime;

    /**
     * 更新时间 更新时间
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", realname=").append(realname);
        sb.append(", face=").append(face);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", sex=").append(sex);
        sb.append(", birthday=").append(birthday);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}