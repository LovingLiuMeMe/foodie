package cn.lovingliu.mapper;

import cn.lovingliu.pojo.ItemsImg;

import java.util.List;

public interface ItemsImgMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemsImg record);

    int insertSelective(ItemsImg record);

    ItemsImg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ItemsImg record);

    int updateByPrimaryKey(ItemsImg record);

    List<ItemsImg> selectByItemId(String itemId);
}