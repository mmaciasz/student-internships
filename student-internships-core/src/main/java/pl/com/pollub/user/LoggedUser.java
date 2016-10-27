package pl.com.pollub.user;


import org.springframework.security.core.authority.AuthorityUtils;
import pl.com.pollub.db.entities.User;

/**
 * Created by mmaciasz on 2016-10-26.
 */
public class LoggedUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public LoggedUser(User user) {
        super(user.getLogin(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
