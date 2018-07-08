package org.launchcode.lifestyletracker.controllers;

import org.launchcode.lifestyletracker.models.Food;
import org.launchcode.lifestyletracker.models.data.FoodDao;
import org.launchcode.lifestyletracker.models.data.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("foods")

public class FoodController {

    @Autowired
    public FoodDao foodDao;

    @Autowired
    public LogDao logDao;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("foods", foodDao.findAll());
        model.addAttribute("title", "My Food List");
        return "foods/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddFoodForm(Model model){
        model.addAttribute("title", "Add a Food");
        model.addAttribute(new Food());
        return "foods/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddFoodForm(@ModelAttribute @Valid Food newFood, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add a Food");
            return "foods/add";
        }
        foodDao.save(newFood);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveFoodForm(Model model){
        model.addAttribute("foods", foodDao.findAll());
        model.addAttribute("title", "Remove a Food");
        return "foods/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveFoodForm(@RequestParam int[] foodIds){
        for (int foodId : foodIds){
            foodDao.delete(foodId);
        }
        return "redirect:";
    }

}
