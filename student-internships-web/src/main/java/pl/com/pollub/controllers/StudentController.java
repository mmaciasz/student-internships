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
 * Created by mateusz on 16.10.16.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FirmRepository firmRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(){
        return "<html><body><div><h1>Hello!</h1></div></body></html>";
    }

    /** TODO Do wyj... niczym kalendarz */
    @RequestMapping(value = "/", method = RequestMethod.GET)
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
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser() {

        User user = new User();
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setEmail("example@example.com");
        user.setUserType(UserType.ADMIN);
        user.setActive(true);

        userRepository.save(user);

        return "User dodany";
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
