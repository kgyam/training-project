package org.example.user.web.service.impl;

import org.apache.commons.lang.StringUtils;
import org.example.user.web.domain.User;
import org.example.user.web.service.IUserService;

import java.util.UUID;

public class UserServiceImpl implements IUserService {
    @Override
    public User register(String nickname, String password, String phoneNum) {
        if (StringUtils.isBlank(nickname) || StringUtils.isBlank(password) || StringUtils.isBlank(phoneNum)) {
            return null;
        }
        password = encrypt(password);
        User user = new User(createId());
        user.setNickname(nickname);
        user.setPassword(password);
        user.setPhoneNumber(phoneNum);
        // TODO: 2021/3/2 dao层持久化

        return user;
    }


    private String createId() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 加密
     *
     * @param password
     * @return
     */
    private String encrypt(String password) {
        // TODO: 2021/3/1 伪加密
        return password;
    }
}
