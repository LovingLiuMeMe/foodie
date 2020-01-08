package cn.lovingliu.mapper;

import cn.lovingliu.pojo.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom {
    void saveComemnts(Map<String,Object> map);
    List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String,Object> map);
}