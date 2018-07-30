package org.launchcode.lifestyletracker.controllers;

import org.launchcode.lifestyletracker.models.Log;
import org.launchcode.lifestyletracker.models.data.LogDao;
import org.launchcode.lifestyletracker.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("logs")

public class LogController {

    @Autowired
    private LogDao logDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model, @CookieValue(value = "user", defaultValue = "none") String username){

        if (username.equals("none")){
            return "redirect:/user/login";
        }

        model.addAttribute("logs", logDao.findAll());
        model.addAttribute("title", "My Daily Logs");

        return "logs/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddLogForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username){

        if (username.equals("none")){
            return "redirect:/user/login";
        }

        model.addAttribute("title", "Add Log Entry");
        model.addAttribute(new Log());

        return "logs/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddLogForm(Model model, @ModelAttribute @Valid Log newLog, Errors errors){

        if (errors.hasErrors()){
            model.addAttribute("title", "My Daily Logs");
            return "logs/add";
        }

        model.addAttribute("title", "Log Entry");
        logDao.save(newLog);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveLogForm(Model model,
                                       @CookieValue(value = "user", defaultValue = "none") String username){

        if (username.equals("none")){
            return "redirect:/user/login";
        }

        model.addAttribute("title", "Remove Log Entry");
        model.addAttribute("logs", logDao.findAll());
        return "logs/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveLogForm(@RequestParam int[] logIds){
        for (int logId : logIds){
            logDao.delete(logId);
        }
        return "redirect:";
    }

}
