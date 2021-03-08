package org.example.user.web.service.impl;

import org.example.user.web.domain.*;
import org.example.user.web.repository.*;
import org.example.user.web.service.UserService;

import javax.annotation.Resource;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Resource(name = "")
    private static UserRepository userRepository;


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

    @Override
    public boolean register(User user) {
        user.setPassword(encrypt(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }
}
