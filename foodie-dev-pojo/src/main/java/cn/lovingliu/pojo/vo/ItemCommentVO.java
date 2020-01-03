package cn.lovingliu.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author：LovingLiu
 * @Description: 商品评论的VO
 * @Date：Created in 2020-01-02
 */
@Data
public class ItemCommentVO {
    private Integer commentLevel;
    private String content;
    private String sepcName;
    private Date createdTime;
    private String userFace;
    private String nickname;
}
