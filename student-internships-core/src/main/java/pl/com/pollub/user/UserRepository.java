package pl.com.pollub.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.com.pollub.db.entities.User;

/**
 * Created by Maciek on 2016-10-21.
 */
@Component
public interface UserRepository extends CrudRepository<User, Integer> {
}
