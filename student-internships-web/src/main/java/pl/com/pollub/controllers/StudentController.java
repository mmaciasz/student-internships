package pl.com.pollub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.pollub.db.entities.User;
import pl.com.pollub.user.UserService;
import pl.com.pollub.user.UserType;

import java.util.List;

/**
 * Created by mateusz on 16.10.16.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> findAll() {

        return userService.findAllStudents();
    }

    @RequestMapping(value = "/{id}")
     public User findById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") Integer id) {
        userService.remove(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@RequestBody User user) {
        return saveOrUpdate(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<User> save(@RequestBody User user) {
        user.setActive(true);
        user.setUserType(UserType.STUDENT);
        return saveOrUpdate(user);
    }

    private ResponseEntity<User> saveOrUpdate(User user) {
        try{
            User savedUser = userService.saveOrUpdate(user);
            return new ResponseEntity(savedUser, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
