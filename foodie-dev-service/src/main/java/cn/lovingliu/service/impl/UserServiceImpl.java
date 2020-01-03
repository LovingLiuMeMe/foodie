package cn.lovingliu.service.impl;

import cn.lovingliu.pojo.bo.UserBO;
import cn.lovingliu.common.enums.Sex;
import cn.lovingliu.common.util.DateUtil;
import cn.lovingliu.common.util.MD5Util;
import cn.lovingliu.mapper.UsersMapper;
import cn.lovingliu.pojo.Users;
import cn.lovingliu.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private static final String DEFAULT_USERS_FACE = "http://pic4.zhimg.com/50/v2-848b1a190d937e270e8d062d00865493_hd.jpg";

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryUsernameIsExist(String username){
        Users users = usersMapper.selectByUsername(username);
        return users != null;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Users createUser(UserBO userBO){
        String usersId = sid.nextShort();
        Users users = new Users();
        users.setId(usersId);
        users.setUsername(userBO.getUsername());
        users.setPassword(MD5Util.MD5EncodeUtf8(userBO.getPassword()));
        users.setNickname(userBO.getUsername());
        users.setFace(DEFAULT_USERS_FACE);
        users.setBirthday(DateUtil.getCurrentDateTime());
        users.setSex(Sex.SECRET.type);
        users.setCreatedTime(DateUtil.getCurrentDateTime());
        users.setUpdatedTime(DateUtil.getCurrentDateTime());

        usersMapper.insertSelective(users);
        return users;
    }

    /**
     * 检索用户名密码是否存在
     * @param username
     * @param password
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public Users queryUserForLogin(String username,String password) {
        return usersMapper.selectByUsernameAndPassword(username,MD5Util.MD5EncodeUtf8(password));
    }
}
