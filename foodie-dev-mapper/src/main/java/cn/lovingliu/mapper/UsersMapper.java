package cn.lovingliu.mapper;

import cn.lovingliu.pojo.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectByUsername(String username);

    Users selectByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}