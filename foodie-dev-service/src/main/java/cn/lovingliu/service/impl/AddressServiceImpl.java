package cn.lovingliu.service.impl;

import cn.lovingliu.common.enums.YesOrNo;
import cn.lovingliu.mapper.UserAddressMapper;
import cn.lovingliu.pojo.UserAddress;
import cn.lovingliu.pojo.bo.AddressBO;
import cn.lovingliu.service.AddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAll(String userId) {
        return userAddressMapper.selectByUserId(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer addNewUserAddress(AddressBO addressBO) {
        // 判断当前用户是否存在地址,如果没有则新增为默认`收货地址`
        Integer isDefault = 0;
        List<UserAddress> addressList = this.queryAll(addressBO.getUserId());
        if(addressList == null || addressList.isEmpty() || addressList.size() == 0){
            isDefault = 1;
        }
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, userAddress);
        userAddress.setId(sid.nextShort());
        userAddress.setIsDefault(isDefault);
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        return userAddressMapper.insertSelective(userAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateUserAddress(AddressBO addressBO) {
        UserAddress updateAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, updateAddress);

        updateAddress.setId(addressBO.getAddressId());
        updateAddress.setUpdatedTime(new Date());
        return userAddressMapper.updateByPrimaryKeySelective(updateAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer deleteUserAddress(String userId, String addressId) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("userId",userId);
        paramsMap.put("addressId",addressId);
        return userAddressMapper.deleteByUserIdAndAddressId(paramsMap);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateUserAddressToBeDefault(String userId, String addressId) {
        // 1.超着默认地址设置为不默认
        Map<String,Object> parmasMap = new HashMap<>();
        parmasMap.put("userId", userId);
        parmasMap.put("isDefault", YesOrNo.YES.type);
        List<UserAddress> userAddressList = userAddressMapper.selectByUserIdAndIsDefault(parmasMap);
        for (UserAddress ua : userAddressList) {
            if (ua != null) {
                ua.setIsDefault(YesOrNo.NO.type);
                userAddressMapper.updateByPrimaryKeySelective(ua);
            }
        }

        // 2.根据地址Id设置默认地址
        UserAddress defaultAddress = new UserAddress();
        defaultAddress.setId(addressId);
        defaultAddress.setUserId(userId);
        defaultAddress.setIsDefault(YesOrNo.YES.type);
        return userAddressMapper.updateByPrimaryKeySelective(defaultAddress);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserAddress queryByAddressIdAndUserId(String userId, String addressId) {
        Map<String,Object> parmasMap = new HashMap<>();
        parmasMap.put("userId", userId);
        parmasMap.put("addressId",addressId);
        return userAddressMapper.selectByPrimaryKeyAndUserId(parmasMap);
    }
}
