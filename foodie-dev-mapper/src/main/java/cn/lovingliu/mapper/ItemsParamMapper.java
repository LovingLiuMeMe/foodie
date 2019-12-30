package cn.lovingliu.mapper;

import cn.lovingliu.pojo.ItemsParam;

public interface ItemsParamMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemsParam record);

    int insertSelective(ItemsParam record);

    ItemsParam selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ItemsParam record);

    int updateByPrimaryKey(ItemsParam record);
}