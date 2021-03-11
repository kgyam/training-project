package org.example.user.web.service.impl;

import org.example.ioc.init.DisposableComponent;
import org.example.user.web.domain.*;
import org.example.user.web.repository.*;
import org.example.user.web.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService, DisposableComponent {

    private static final Logger logger = Logger.getLogger (UserServiceImpl.class.getName ());
    @Resource(name = "bean/UserRepository")
    private UserRepository userRepository;

    @Resource(name = "bean/UserValidator")
    private Validator validator;


    @PostConstruct
    private void postConstruct() {
        logger.info ("post construct");
    }


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
        try {

            Set<ConstraintViolation<User>> validResult = validator.validate (user);
            if (validResult.size () > 0) {
                for (ConstraintViolation<User> userConstraintViolation : validResult) {
                    logger.info ("错误:" + userConstraintViolation.getMessage ());
                    logger.info ("字段:" + userConstraintViolation.getPropertyPath ().toString ());
                }
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException (e);
        }
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

    @Override
    public void init() {
        logger.info ("init by DisposableComponent#init");
    }
}
