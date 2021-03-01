package org.example.user.web.service;

import org.example.user.web.domain.User;

public interface IUserService {

    User register(String nickname, String password, String phoneNum);

}
