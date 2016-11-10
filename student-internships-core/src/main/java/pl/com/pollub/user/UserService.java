package pl.com.pollub.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import pl.com.pollub.db.entities.User;
import pl.com.pollub.exception.ValidationException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

/**
 * Created by mmaciasz on 2016-10-26.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllStudents() {
        return userRepository.findByUserTypeAndActive(UserType.STUDENT, Boolean.TRUE);
    }

    public List<User> findAllPromoters() {
        return userRepository.findByUserTypeAndActive(UserType.TUTOR, Boolean.TRUE);
    }

    public User findById(Integer id) {
        return userRepository.findOne(id);
    }

    public void remove(Integer id) {
        userRepository.delete(id);
    }

    public User saveOrUpdate(User user) throws UnsupportedEncodingException {

        if(!validate(user)) {
            throw new ValidationException();
        }

        if(!StringUtils.isEmpty(user.getNewPassword())) {
            byte[] passwordBytes = user.getNewPassword().getBytes("UTF-8");
            user.setPassword(DigestUtils.md5DigestAsHex(passwordBytes));
        }

        return userRepository.save(user);
    }

    private boolean validate(User user) {

        if("".equals(user.getNewPassword())) {
            user.setNewPassword(null);
        }

        if("".equals(user.getConfirmedNewPassword())) {
            user.setConfirmedNewPassword(null);
        }

        if(!Objects.equals(user.getNewPassword(), user.getConfirmedNewPassword())) {
            return false;
        }

        return true;
    }

}
