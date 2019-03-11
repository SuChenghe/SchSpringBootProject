package com.suchenghe.web;

import com.suchenghe.dao.mysql.pojo.UserInfo;
import com.suchenghe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author SuChenghe
 * @date 2018/12/16 23:39
 */
@Controller("index_controller")
@RequestMapping("/")
public class IndexController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap modelMap) {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserInfo user, HttpSession session) {
        UserInfo loginUser = userService.getUserInfo(user);
        if (null != loginUser) {
            session.setAttribute("username", loginUser.getUserName());
            return "redirect:/index";
        } else {
            return "/login";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(ModelMap modelMap, HttpSession session) {
        //清除session
        session.invalidate();
        return "/login";
    }

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        return "/index";
    }


}
