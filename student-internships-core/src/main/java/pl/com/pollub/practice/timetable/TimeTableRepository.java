package pl.com.pollub.practice.timetable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.com.pollub.db.entities.TimetableNode;

/**
 * Created by root on 05.01.17.
 */
@Repository
public interface TimeTableRepository extends CrudRepository<TimetableNode, Integer> {

}
