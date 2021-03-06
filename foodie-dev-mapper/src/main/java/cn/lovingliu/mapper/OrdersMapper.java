package cn.lovingliu.mapper;

import cn.lovingliu.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface OrdersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    Orders selectByOrderIdAndUserId(@Param("parmasMap")Map<String,Object> parmasMap);
}