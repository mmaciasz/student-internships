package pl.com.pollub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.com.pollub.db.entities.Firm;
import pl.com.pollub.db.entities.User;
import pl.com.pollub.firm.FirmRepository;
import pl.com.pollub.user.UserRepository;
import pl.com.pollub.user.UserType;

/**
 * Created by mmaciasz on 2016-10-26.
 */
@RestController
public class TestController {

    private final UserRepository userRepository;
    private final FirmRepository firmRepository;

    @Autowired
    public TestController(UserRepository userRepository, FirmRepository firmRepository) {
        this.userRepository = userRepository;
        this.firmRepository = firmRepository;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser() {

        User user = new User();
        user.setLogin("Admin");
        user.setPassword("21232f297a57a5a743894a0e4a801fc3");
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setEmail("example@example.com");
        user.setUserType(UserType.ADMIN);
        user.setActive(true);

        userRepository.save(user);

        return "User dodany";
    }


    /** TODO Do wyj... niczym kalendarz */
    @RequestMapping(value = "/testData", method = RequestMethod.GET)
    public String getUsers() {

        Iterable<User> users = userRepository.findAll();

        if(!users.iterator().hasNext()) {
            return "pusta baza";
        }

        final StringBuilder builder = new StringBuilder();

        users.forEach(user -> {
            builder.append(user.getFirstName() + " " + user.getLastName());
            builder.append("<br>");
        });

        return builder.toString();
    }

    /** TODO Do wyj... niczym kalendarz */
    @RequestMapping(value = "/addFirm", method = RequestMethod.GET)
    public String addFirm() {

        Firm firm = new Firm();
        firm.setName("Zolty atos");
        firm.setAddress("adres");
        firm.setContact("kontakt");
        firmRepository.save(firm);
        return "Firma dodana";
    }
}
