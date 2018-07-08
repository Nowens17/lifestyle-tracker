package org.launchcode.lifestyletracker.models.data;

import org.launchcode.lifestyletracker.models.Food;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nick Owens
 */

public interface FoodDao extends CrudRepository<Food, Integer> {
}
