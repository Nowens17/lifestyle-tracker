package org.launchcode.lifestyletracker.controllers;

import org.launchcode.lifestyletracker.models.User;
import org.launchcode.lifestyletracker.models.data.LogDao;
import org.launchcode.lifestyletracker.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")

public class HomeController {

    @Autowired
    private LogDao logDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model, @CookieValue(value = "user", defaultValue = "none") String username){

        if (username.equals("none")){
            return "redirect:/user/login";
        }

        //Incorporate when home screen display is implemented
        //User u = userDao.findByUsername(username).get(0);

        model.addAttribute("title", "My Lifestyle Tracker");

        return "home/index";
    }






}
