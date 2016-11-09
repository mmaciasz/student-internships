package pl.com.pollub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.pollub.db.entities.Firm;
import pl.com.pollub.db.entities.User;
import pl.com.pollub.firm.FirmService;
import pl.com.pollub.user.UserType;

import java.util.List;

/**
 * Created by mmaciasz on 2016-11-09.
 */
@RestController
@RequestMapping("/firm")
public class FirmContoller {

    private final FirmService firmService;

    @Autowired
    public FirmContoller(FirmService firmService) {
        this.firmService = firmService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Firm> findAll() {
        return firmService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Firm findOne(@PathVariable("id") Integer id) {
        return firmService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteFirm(@PathVariable("id") Integer id) {
        firmService.deleteFirm(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Firm> update(@RequestBody Firm firm) {
        return saveOrUpdate(firm);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Firm> save(@RequestBody Firm firm) {
        return saveOrUpdate(firm);
    }

    private ResponseEntity<Firm> saveOrUpdate(Firm firm) {
        try {
            Firm savedFirm = firmService.saveOrUpdate(firm);
            return new ResponseEntity<>(savedFirm, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.GET)
    public List<User> findAllEmployees() {
        return firmService.findAllEmployees();
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public User findOneEmployee(@PathVariable("id") Integer id) {
        return firmService.findEmployeeById(id);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable("id") Integer id) {
        firmService.deleteEmployee(id);
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.PUT)
    public ResponseEntity<User> updateEmployee(@RequestBody User user) {
        return saveOrUpdateEmployee(user);
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity<User> saveEmployee(@RequestBody User user) {
        user.setActive(true);
        user.setUserType(UserType.FIRM_EMPLOYEE);
        return saveOrUpdateEmployee(user);
    }

    private ResponseEntity<User> saveOrUpdateEmployee(User user) {
        try {
            User savedEmployee = firmService.saveEmployee(user);
            return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
