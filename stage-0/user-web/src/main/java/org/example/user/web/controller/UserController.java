package org.example.user.web.controller;

import org.example.web.mvc.controller.Controller;
import org.example.web.mvc.controller.HttpMethod;
import org.example.web.mvc.controller.RequestMapping;
import org.example.web.mvc.controller.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * @author kg yam
 * @date 2021-03-01 17:33
 * @since
 */
@RequestMapping(value = "/user")
public class UserController implements Controller {

    private static final Logger LOGGER = Logger.getLogger (UserController.class.getName ());

    @RequestMethod({HttpMethod.POST,HttpMethod.GET})
    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter ("username");
        String password = request.getParameter ("password");
        LOGGER.info ("register..");
        LOGGER.info ("username: " + username + ",password: " + password);
        return "";
    }

}
