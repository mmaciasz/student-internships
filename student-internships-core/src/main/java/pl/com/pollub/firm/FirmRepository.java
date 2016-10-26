package pl.com.pollub.firm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.com.pollub.db.entities.Firm;

/**
 * Created by Maciek on 2016-10-21.
 */
@Repository
public interface FirmRepository extends CrudRepository<Firm, Integer> {
}
