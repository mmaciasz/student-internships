package pl.com.pollub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.pollub.db.entities.Firm;
import pl.com.pollub.db.entities.PracticeDefinition;
import pl.com.pollub.db.entities.User;
import pl.com.pollub.practice.PracticeService;
import pl.com.pollub.practice.definition.PracticeDefSearchCriteria;
import pl.com.pollub.practice.definition.PracticeDefinitionStatus;
import pl.com.pollub.util.ApplicationUtils;

import java.util.List;

/**
 * Created by Maciek on 2016-12-08.
 */
@RestController
@RequestMapping("/practiceDefinitions")
public class PracticeDefinitionController {

    @Autowired
    private PracticeService practiceService;

    @RequestMapping("/")
    public List<PracticeDefinition> findPracticeDefinitions() {

        User loggedUser = ApplicationUtils.getLoggedUser();
        PracticeDefSearchCriteria criteria = new PracticeDefSearchCriteria();

        switch(loggedUser.getUserType()) {
            case FIRM_EMPLOYEE:
                Firm firm = loggedUser.getFirm();
                criteria.setFirmId(firm.getFirmId());
                break;
            case STUDENT:
                criteria.setStatus(PracticeDefinitionStatus.ACTIVE);
                break;
            default:
                break;
        }

        return practiceService.searchPracticeDefinitions(criteria);
    }

    @RequestMapping(value = "/{id}")
    public PracticeDefinition findById(@PathVariable("id") int id) {
        return practiceService.findPracticeDefinitionById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") Integer id) {
        practiceService.removePracticeDefinition(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<PracticeDefinition> update(@RequestBody PracticeDefinition practiceDefinition) {
        return saveOrUpdate(practiceDefinition);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<PracticeDefinition> save(@RequestBody PracticeDefinition practiceDefinition) {

        User loggedUser = ApplicationUtils.getLoggedUser();

        practiceDefinition.setFirm(loggedUser.getFirm());
        practiceDefinition.setStatus(PracticeDefinitionStatus.INACTIVE);
        return saveOrUpdate(practiceDefinition);
    }

    private ResponseEntity<PracticeDefinition> saveOrUpdate(PracticeDefinition practiceDefinition) {
        try{
            PracticeDefinition savedPracticeDef = practiceService.saveOrUpdatePracticeDefinition(practiceDefinition);
            return new ResponseEntity(savedPracticeDef, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
