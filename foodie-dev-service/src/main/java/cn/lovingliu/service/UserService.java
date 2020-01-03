package cn.lovingliu.service;

import cn.lovingliu.pojo.bo.UserBO;
import cn.lovingliu.pojo.Users;

public interface UserService {
    boolean queryUsernameIsExist(String username);
    Users createUser(UserBO userBO);
    Users queryUserForLogin(String username,String password);
}
