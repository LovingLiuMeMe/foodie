package cn.lovingliu.mapper;

import cn.lovingliu.pojo.vo.ItemCommentVO;
import cn.lovingliu.pojo.vo.SearchItemsVO;
import cn.lovingliu.pojo.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String,Object> paramsMap);
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String,Object> paramsMap);
    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String,Object> paramsMap);
    List<ShopcartVO> queryItemsBySpecIds(@Param("specIdList") List<String> specIdList);
    int decreaseItemSpecStock(@Param("paramsMap") Map<String,Object> paramsMap);
}