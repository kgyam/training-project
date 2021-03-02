package org.example.user.web.service.impl;

import org.example.user.web.domain.User;
import org.example.user.web.repository.DatabaseUserRepository;
import org.example.user.web.repository.UserRepository;
import org.example.user.web.service.UserService;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    private static UserRepository userRepository = new DatabaseUserRepository ();


    private String createId() {
        return UUID.randomUUID ().toString ().replace ("-", "");
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
        // TODO: 2021/3/2 repository层持久化
        user.setPassword (encrypt (user.getPassword ()));
        return userRepository.save (user);
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
