package cn.lovingliu.mapper;

import cn.lovingliu.pojo.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserAddressMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    List<UserAddress> selectByUserId(String userId);

    int deleteByUserIdAndAddressId(@Param("parmasMap") Map<String,Object> parmasMap);

    List<UserAddress> selectByUserIdAndIsDefault(@Param("parmasMap") Map<String,Object> parmasMap);

    UserAddress selectByPrimaryKeyAndUserId(@Param("parmasMap") Map<String,Object> parmasMap);
}