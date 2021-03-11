package org.example.user.web.controller;

import org.apache.commons.lang.StringUtils;
import org.example.user.web.domain.*;
import org.example.user.web.service.UserService;
import org.example.web.mvc.controller.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * @author kg yam
 * @date 2021-03-01 17:33
 * @since
 */
@RequestMapping(value = "/user")
public class UserController implements PageController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    @Resource(name = "bean/UserService")
    private UserService userService;

    @RequestMethod({HttpMethod.POST})
    @RequestMapping(value = "/registry")
    public String registry(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String name = request.getParameter("name");
        name = new String(name.getBytes("iso8859-1"), "UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNum = request.getParameter("phoneNum");

        if (StringUtils.isBlank(name) || StringUtils.isBlank(email)
                || StringUtils.isBlank(phoneNum) || StringUtils.isBlank(password)) {
            return "failed.jsp";
        }
        User user = new User(name, password, email, phoneNum);
        LOGGER.info(user.toString());
        if (userService.register(user)) {
            response.setCharacterEncoding("utf-8");
            request.setAttribute("user", user);
            return "success.jsp";
        }
        return "failed.jsp";
    }


    @RequestMethod({HttpMethod.GET})
    @RequestMapping(value = "/registry-form")
    public String registryForm(HttpServletRequest request, HttpServletResponse response) {
        return "registry-form.jsp";
    }
}
