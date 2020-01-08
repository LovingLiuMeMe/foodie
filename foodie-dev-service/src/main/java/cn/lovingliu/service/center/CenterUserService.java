package cn.lovingliu.service.center;

import cn.lovingliu.pojo.Users;
import cn.lovingliu.pojo.bo.center.CenterUserBO;

public interface CenterUserService {
    /**
     * 根据用户Id查询信息
     * @param userId
     * @return
     */
    Users queryUserInfo(String userId);

    /**
     * 修改用户信息
     * @param userId
     * @param centerUserBO
     * @return
     */
    Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     * @param userId
     * @param faceUrl
     * @return
     */
    Users updateUserFace(String userId,String faceUrl);
}
