package org.launchcode.lifestyletracker.models.data;

import org.launchcode.lifestyletracker.models.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Nick Owens
 */
@Repository
@Transactional

public interface LogDao extends CrudRepository<Log, Integer> {
}
