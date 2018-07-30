package org.launchcode.lifestyletracker.controllers;

import org.launchcode.lifestyletracker.models.Food;
import org.launchcode.lifestyletracker.models.User;
import org.launchcode.lifestyletracker.models.data.FoodDao;
import org.launchcode.lifestyletracker.models.data.LogDao;
import org.launchcode.lifestyletracker.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Nick Owens
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    LogDao logDao;

    @Autowired
    FoodDao foodDao;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model){
        model.addAttribute("title", "User Sign-up");
        User user = new User();
        model.addAttribute("user", user);
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUserForm(Model model,
                                     @ModelAttribute @Valid User user, Errors errors, String verify){
        List<User> nameCheck = userDao.findByUsername(user.getUsername());
        if (!errors.hasErrors() && user.getPassword().equals(verify) && nameCheck.isEmpty()){
            model.addAttribute("user", user);
            userDao.save(user);



            return "home/index";
        } else {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("title", "User Sign-up");
            if (!user.getPassword().equals(verify)) {
                model.addAttribute("message", "Passwords do not match");
                user.setPassword("");
            }
            if (!nameCheck.isEmpty()){
                model.addAttribute("message",
                        "Username is taken, please select another one");
            }
            return "user/add";
        }
    }

    @RequestMapping(value = "login")
    public String loginForm(Model model) {
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
            return "home/index";
        } else {
            model.addAttribute("message", "Invalid Password");
            model.addAttribute("title", "Login Form");
            return "user/login";
        }
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        return "user/login";
    }
}
