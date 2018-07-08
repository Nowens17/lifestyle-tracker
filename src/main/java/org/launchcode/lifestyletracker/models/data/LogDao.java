package org.launchcode.lifestyletracker.models.data;

import org.launchcode.lifestyletracker.models.Log;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nick Owens
 */

public interface LogDao extends CrudRepository<Log, Integer> {
}
