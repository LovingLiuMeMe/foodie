package cn.lovingliu.mapper;

import cn.lovingliu.pojo.ItemsImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsImgMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemsImg record);

    int insertSelective(ItemsImg record);

    ItemsImg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ItemsImg record);

    int updateByPrimaryKey(ItemsImg record);

    List<ItemsImg> selectByItemId(String itemId);

    ItemsImg selectByItemIdAndIsMain(@Param("parmasMap") Map<String,Object> parmasMap);
}