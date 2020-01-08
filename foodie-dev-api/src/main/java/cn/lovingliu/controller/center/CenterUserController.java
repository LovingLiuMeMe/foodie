package cn.lovingliu.controller.center;

import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.common.resource.FileUpload;
import cn.lovingliu.common.util.CookieUtils;
import cn.lovingliu.common.util.DateUtil;
import cn.lovingliu.common.util.JsonUtils;
import cn.lovingliu.pojo.Users;
import cn.lovingliu.pojo.bo.center.CenterUserBO;
import cn.lovingliu.service.center.CenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "用户信息接口", tags = "用户信息相关的API")
@RestController
@RequestMapping("/userInfo")
public class CenterUserController {
//    private static final String IMAGE_USER_FACE_LOCATION = File.separator+"Users"
//            +File.separator+"lovingliu"
//            +File.separator+"Desktop"
//            +File.separator+"HFTEC"
//            +File.separator+"pictures";

    @Autowired
    private CenterUserService centerUserService;
    @Autowired
    private FileUpload fileUpload;

    @ApiOperation(value = "用户头像上传",notes = "用户头像上传",httpMethod = "POST")
    @PostMapping("/uploadFace")
    public ServerResponse uploadFace(@RequestParam String userId,
                                 MultipartFile file,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try{
            // 头像保存地址zengjia
            // String fileSpace = IMAGE_USER_FACE_LOCATION;
            String fileSpace = fileUpload.getImageUserFaceLocation();
            // 在路径上为每一个用户增加一个userid,用于区分不同的用户上传
            String uploadPathPrefix = File.separator + userId;

            // 开始文件上传
            if(file == null){
                return ServerResponse.createByErrorMessage("文件不能为空");
            }
            /**
             * 1.文件名设置
             */
            // 获得文件上传的名称
            String fileName = file.getOriginalFilename();
            if (StringUtils.isBlank(fileName)){
                return ServerResponse.createByErrorMessage("文件名不能为空");
            }
            String[] fileNameArray = fileName.split("\\."); // 123.png -> ["123","png"]
            String suffix = fileNameArray[fileNameArray.length - 1]; // 获取文件后缀名
            if (!suffix.equalsIgnoreCase("png")&& // 限制指定图片格式
                !suffix.equalsIgnoreCase("jpg")&&
                !suffix.equalsIgnoreCase("jpeg")){
                return ServerResponse.createByErrorMessage("图片格式错误");
            }
            String newFileName = "face-" + userId + "." + suffix; // 文件名称重组 覆盖式上传 face-{userid}.png
            /**
             * 2.设置文件保存路径
             */
            String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;
            /**
             * 3.构建文件保存对象
             */
            File outFile = new File(finalFacePath);
            if (outFile.getParentFile() != null) {
                // 创建文件夹
                outFile.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(outFile);
            inputStream = file.getInputStream();
            IOUtils.copy(inputStream, fileOutputStream);
            /**
             * 4.更新用户头像
             */
            String imageServerUrl = fileUpload.getImageServerUrl();
            String finalUserFaceUrl = imageServerUrl + uploadPathPrefix + "/" + newFileName+ "?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);
            Users usersResult = centerUserService.updateUserFace(userId, finalUserFaceUrl);
            usersResult = setNullProperty(usersResult);
            // TODO 后续要该 增加令牌 会整合redis，分布式会话
            CookieUtils.setCookie(request, response,"user", JsonUtils.objectToJson(usersResult),true);
            return ServerResponse.createBySuccess(usersResult);
        } catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("用户头像上传失败");
        } finally {
            try{
                if (fileOutputStream != null){
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if (inputStream != null){
                    inputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @ApiOperation(value = "修改用户信息",notes = "修改用户信息",httpMethod = "POST")
    @PostMapping("/update")
    public ServerResponse update(@RequestParam String userId,
                                 @Valid @RequestBody CenterUserBO centerUserBO,
                                 BindingResult bindingResult,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = getErrors(bindingResult);
            return ServerResponse.createByError();
        }
        Users usersResult = centerUserService.updateUserInfo(userId, centerUserBO);
        usersResult = setNullProperty(usersResult);
        CookieUtils.setCookie(request, response,"user", JsonUtils.objectToJson(usersResult),true);
        // TODO 后续要该 增加令牌 会整合redis，分布式会话
        return ServerResponse.createBySuccess(usersResult);
    }

    private Users setNullProperty(Users usersResult){
        usersResult.setPassword(null);
        usersResult.setMobile(null);
        usersResult.setEmail(null);
        usersResult.setCreatedTime(null);
        usersResult.setCreatedTime(null);
        usersResult.setBirthday(null);
        return usersResult;
    }

    private Map<String,String> getErrors(BindingResult bindingResult){
        List<FieldError> errorList = bindingResult.getFieldErrors();
        Map<String,String> errorMap = new HashMap<>();
        for (FieldError fieldError: errorList) {
            // 发生验证错误所对应的某一个属性
            String errorField = fieldError.getField();
            // 错误信息
            String errorMsg = fieldError.getDefaultMessage();

            errorMap.put(errorField,errorMsg);
        }
        return errorMap;
    }
}
