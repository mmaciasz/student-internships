package pl.com.pollub.practice.definition;

import org.springframework.data.repository.CrudRepository;
import pl.com.pollub.db.entities.PracticeDefinition;

import java.util.List;

/**
 * Created by Maciek on 2016-12-08.
 */
public interface PracticeDefinitionRepository extends CrudRepository<PracticeDefinition, Integer> {

    String BEAN_ID = "PracticeDefinitionRepository";

    List<PracticeDefinition> searchByCriteria(PracticeDefSearchCriteria criteria);

}
