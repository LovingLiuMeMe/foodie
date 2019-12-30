package cn.lovingliu.mapper;

import cn.lovingliu.pojo.UserAddress;

public interface UserAddressMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
}