package org.example.user.web.controller;

import org.example.user.web.domain.User;
import org.example.user.web.service.UserService;
import org.example.user.web.service.impl.UserServiceImpl;
import org.example.web.mvc.controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * @author kg yam
 * @date 2021-03-01 17:33
 * @since
 */
@RequestMapping(value = "/user")
public class UserController implements PageController {

    private static final Logger LOGGER = Logger.getLogger (UserController.class.getName ());
    private static UserService userService = new UserServiceImpl ();

    @RequestMethod({HttpMethod.POST})
    @RequestMapping(value = "/registry")
    public String registry(HttpServletRequest request, HttpServletResponse response) {
        String nickName = (String)request.getAttribute ("nickName");
        String email = (String)request.getAttribute ("email");
        String password = (String)request.getAttribute ("password");
        String phoneNum =  (String)request.getAttribute ("phoneNum");
        User user = new User (nickName, password, email, phoneNum);
        userService.register (user);
        return "success.jsp";
    }


    @RequestMethod({HttpMethod.GET})
    @RequestMapping(value = "/registry-form")
    public String registryForm(HttpServletRequest request, HttpServletResponse response) {
        return "registry-form.jsp";
    }
}
