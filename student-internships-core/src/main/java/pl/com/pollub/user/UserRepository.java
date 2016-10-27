package pl.com.pollub.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.com.pollub.db.entities.User;

/**
 * Created by Maciek on 2016-10-21.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findFirstByLogin(String login);
}
