package pl.com.pollub.firm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import pl.com.pollub.db.entities.Firm;
import pl.com.pollub.db.entities.User;
import pl.com.pollub.exception.ValidationException;
import pl.com.pollub.user.UserRepository;
import pl.com.pollub.user.UserType;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by mmaciasz on 2016-11-09.
 */
@Service
@Transactional
public class FirmService {

    private final FirmRepository firmRepository;
    private final UserRepository userRepository;

    @Autowired
    public FirmService(FirmRepository firmRepository, UserRepository userRepository) {
        this.firmRepository = firmRepository;
        this.userRepository = userRepository;
    }

    public List<Firm> findAll() {
        return StreamSupport.stream(firmRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Firm findById(Integer id) {
        return firmRepository.findOne(id);
    }

    public void deleteFirm(Integer id) {
        firmRepository.delete(id);
    }

    public Firm saveOrUpdate(Firm firm) {
        return firmRepository.save(firm);
    }

    public List<User> findAllEmployees() {
        return userRepository.findByUserTypeAndActive(UserType.FIRM_EMPLOYEE, true);
    }

    public User findEmployeeById(Integer id) {
        return userRepository.findOne(id);
    }

    public void deleteEmployee(Integer id) {
        userRepository.delete(id);
    }

    public User saveEmployee(User user) throws UnsupportedEncodingException {
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
        return Objects.equals(user.getNewPassword(), user.getConfirmedNewPassword());

    }
}
