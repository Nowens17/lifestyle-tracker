package org.launchcode.lifestyletracker.controllers;

import org.launchcode.lifestyletracker.models.User;
import org.launchcode.lifestyletracker.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Nick Owens
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "add")
    public String addUser(Model model){
        model.addAttribute("title", "User Sign-up");
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(Model model, @ModelAttribute User user, String verify){
        if (verify.equals(user.getPassword())){
            model.addAttribute("user", user);
            return "user/index";
        } else {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("title", "User Sign-up");
            model.addAttribute("message", "Passwords do not match");
            return "user/add";
        }
    }

    @RequestMapping(value = "login")
    public String add(Model model) {
        model.addAttribute("title", "Login Form");
        model.addAttribute(new User());
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, HttpServletResponse response){
        List<User> u = userDao.findByUsername(user.getUsername());
        if (u.isEmpty()){
            model.addAttribute("message", "Invalid Username");
            model.addAttribute("title", "Login Form");
            return "user/login";
        }

        User loggedIn = u.get(0);
        if (loggedIn.getPassword().equals(user.getPassword())){
            Cookie cookie = new Cookie("user", user.getUsername());
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:";
        } else {
            model.addAttribute("message", "Invalid Password");
            model.addAttribute("title", "Login Form");
            return "user/login";
        }
    }


}
