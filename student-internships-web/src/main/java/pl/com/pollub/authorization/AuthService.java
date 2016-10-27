package pl.com.pollub.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.com.pollub.db.entities.User;
import pl.com.pollub.user.LoggedUser;
import pl.com.pollub.user.UserRepository;

import java.util.Optional;

/**
 * Created by mmaciasz on 2016-10-26.
 */
@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoggedUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findFirstByLogin(login))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with login: %s not found", login)));
        return new LoggedUser(user);
    }
}
