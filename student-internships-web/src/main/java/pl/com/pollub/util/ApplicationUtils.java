package pl.com.pollub.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.com.pollub.db.entities.User;
import pl.com.pollub.user.LoggedUser;

/**
 * Created by Maciek on 2016-12-13.
 */
public class ApplicationUtils {

    public static User getLoggedUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoggedUser loggedUser = (LoggedUser) auth.getPrincipal();
        return loggedUser.getUser();
    }

}
