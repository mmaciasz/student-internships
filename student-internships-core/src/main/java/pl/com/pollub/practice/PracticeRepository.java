package pl.com.pollub.practice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.com.pollub.db.entities.Practice;
import pl.com.pollub.practice.definition.PracticeDefSearchCriteria;

import java.util.List;

/**
 * Created by Maciek on 2016-12-08.
 */
//@Repository
public interface PracticeRepository extends CrudRepository<Practice, Integer> {

    String BEAN_ID = "PracticeRepository";

    List<Practice> searchByCriteria(PracticeSearchCriteria criteria);

}
