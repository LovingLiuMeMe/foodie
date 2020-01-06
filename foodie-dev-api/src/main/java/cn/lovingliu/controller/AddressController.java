package cn.lovingliu.controller;

import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.pojo.UserAddress;
import cn.lovingliu.pojo.bo.AddressBO;
import cn.lovingliu.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 收货地址Controller
 * @Date：Created in 2020-01-03
 */
@Api(value = "地址相关",tags = "地址相关接口")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "查询用户所有的收货地址",notes = "查询用户所有的收货地址",httpMethod = "POST")
    @PostMapping("/list")
    public ServerResponse<List<UserAddress>> list(@RequestParam String userId) {
        if(StringUtils.isBlank(userId)){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        List<UserAddress> addressList = addressService.queryAll(userId);
        return ServerResponse.createBySuccess(addressList);
    }

    @ApiOperation(value = "新增收货地址",notes = "新增收货地址",httpMethod = "POST")
    @PostMapping("/add")
    public ServerResponse add(@Valid @RequestBody AddressBO addressBO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ServerResponse.createByErrorMessage(bindingResult.getFieldError().getDefaultMessage());
        }
        int count = addressService.addNewUserAddress(addressBO);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("新增收货地址成功");
        }else {
            return ServerResponse.createByErrorMessage("新增收货地址失败");
        }
    }

    @ApiOperation(value = "修改收货地址",notes = "修改收货地址",httpMethod = "POST")
    @PostMapping("/update")
    public ServerResponse update(@RequestBody AddressBO addressBO){
        if(StringUtils.isBlank(addressBO.getAddressId())){
            return ServerResponse.createByErrorMessage("收货地址不存在");
        }
        int count = addressService.updateUserAddress(addressBO);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("修改收货地址成功");
        }else {
            return ServerResponse.createByErrorMessage("修改收货地址失败");
        }
    }

    @ApiOperation(value = "删除收货地址",notes = "删除收货地址",httpMethod = "POST")
    @PostMapping("/delete")
    public ServerResponse delete(@RequestParam String userId,@RequestParam String addressId){
        if(StringUtils.isBlank(userId)){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        if(StringUtils.isBlank(addressId)){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        int count = addressService.deleteUserAddress(userId, addressId);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("删除收货地址成功");
        }else {
            return ServerResponse.createByErrorMessage("删除收货地址失败");
        }
    }

    @ApiOperation(value = "用户设置默认地址",notes = "用户设置默认地址",httpMethod = "POST")
    @PostMapping("/setDefalut")
    public ServerResponse setDefalut(@RequestParam String userId,@RequestParam String addressId){
        if(StringUtils.isBlank(userId)){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        if(StringUtils.isBlank(addressId)){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        int count = addressService.updateUserAddressToBeDefault(userId, addressId);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("设置默认收货地址成功");
        }else {
            return ServerResponse.createByErrorMessage("设置默认收货地址失败");
        }
    }
}
