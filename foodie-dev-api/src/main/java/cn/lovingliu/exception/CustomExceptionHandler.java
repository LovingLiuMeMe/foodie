package cn.lovingliu.exception;

import cn.lovingliu.common.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @Author：LovingLiu
 * @Description: 统一异常捕获
 * @Date：Created in 2020-01-07
 */
@RestControllerAdvice
public class CustomExceptionHandler {
    // 上传文件超过500K,捕获异常: MaxUploadSizeExceededException
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ServerResponse handleMaxUploadFile(MaxUploadSizeExceededException exception){
        return ServerResponse.createByErrorMessage("上传文件的大小不能超过500K,请压缩图片或者降低图片质量");
    }
}
