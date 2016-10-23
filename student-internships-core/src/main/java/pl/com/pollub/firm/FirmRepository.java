package pl.com.pollub.firm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.com.pollub.db.entities.Firm;

/**
 * Created by Maciek on 2016-10-21.
 */
@Component
public interface FirmRepository extends CrudRepository<Firm, Integer> {
}
