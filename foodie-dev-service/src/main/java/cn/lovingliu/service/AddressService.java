package cn.lovingliu.service;

import cn.lovingliu.pojo.UserAddress;
import cn.lovingliu.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {
    /**
     * 根据用户Id查询用户的收货列表
     * @param userId
     * @return
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 增加用户的收货地址
     * @param addressBO
     * @return
     */
    Integer addNewUserAddress(AddressBO addressBO);

    /**
     * 修改用户地址
     * @param addressBO
     * @return
     */
    Integer updateUserAddress(AddressBO addressBO);

    /**
     * 根据用户Id,地址Id 删除用户地址
     * @param userId
     * @param addressId
     * @return
     */
    Integer deleteUserAddress(String userId, String addressId);

    /**
     * 修改默认地址
     * @param userId
     * @param addressId
     * @return
     */
    Integer updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 根据UserId和addressId 查询收货地址信息
     * @param userId
     * @param addressId
     * @return
     */
    UserAddress queryByAddressIdAndUserId(String userId, String addressId);
}
