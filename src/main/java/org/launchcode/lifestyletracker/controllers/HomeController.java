package org.launchcode.lifestyletracker.controllers;

import org.launchcode.lifestyletracker.models.data.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")

public class HomeController {

    @Autowired
    private LogDao logDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title", "My Lifestyle Tracker");

        return "home/index";
    }






}
