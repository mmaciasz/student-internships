package pl.com.pollub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.com.pollub.db.entities.Firm;
import pl.com.pollub.db.entities.Practice;
import pl.com.pollub.db.entities.PracticeDefinition;
import pl.com.pollub.db.entities.User;
import pl.com.pollub.practice.PracticeSearchCriteria;
import pl.com.pollub.practice.definition.PracticeDefSearchCriteria;
import pl.com.pollub.practice.definition.PracticeDefinitionStatus;
import pl.com.pollub.practice.PracticeService;
import pl.com.pollub.practice.PracticeStatus;
import pl.com.pollub.util.ApplicationUtils;

import java.util.List;

/**
 * Created by Maciek on 2017-01-02.
 */
@RestController
@RequestMapping("/practices")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @RequestMapping("/")
    public List<Practice> findPractices() {

        User loggedUser = ApplicationUtils.getLoggedUser();
        PracticeSearchCriteria criteria = new PracticeSearchCriteria();

        switch(loggedUser.getUserType()) {
            case FIRM_EMPLOYEE:
                Firm firm = loggedUser.getFirm();
                criteria.setFirmId(firm.getFirmId());
                break;
            case STUDENT:
                criteria.setStudentId(loggedUser.getUserId());
                break;
            default:
                break;
        }

        List<Practice> practices = practiceService.searchPractices(criteria);
        return practices;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Practice> save(@RequestBody Practice practice) {

        User loggedUser = ApplicationUtils.getLoggedUser();

        practice.setStudent(loggedUser);
        practice.setStatus(PracticeStatus.WAITING_FOR_APPROVAL);
        return saveOrUpdate(practice);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Practice> update(@RequestBody Practice practice) {
        return saveOrUpdate(practice);
    }

    private ResponseEntity<Practice> saveOrUpdate(Practice practice) {
        try{
            Practice savedPractice = practiceService.saveOrUpdatePractice(practice);
            return new ResponseEntity(savedPractice, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
