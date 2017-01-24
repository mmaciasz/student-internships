package pl.com.pollub.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.pollub.db.entities.TimetableNode;
import pl.com.pollub.practice.timetable.TimeTableService;

import java.util.List;

/**
 * Created by root on 09.01.17.
 */
@RestController
@RequestMapping("/timetable")
public class TimeTableController {

    private static final Logger log = LoggerFactory.getLogger(TimeTableController.class);

    private final TimeTableService timeTableService;

    @Autowired
    public TimeTableController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @RequestMapping(value = "/findSchedule/{id}", method = RequestMethod.GET)
    public List<TimetableNode> findSchedule(@PathVariable("id") final Integer practiceId) {
        return timeTableService.findSchedule4Practice(practiceId);
    }

    @RequestMapping(value = "/saveTimetable", method = RequestMethod.POST)
    public ResponseEntity<List<TimetableNode>> saveSchedule(@RequestBody final List<TimetableNode> scheduleNodes) {
        return saveOrUpdateTimeTable(scheduleNodes);
    }

    @RequestMapping(value = "/findDiary/{id}", method = RequestMethod.GET)
    public List<TimetableNode> findDiary(@PathVariable("id") final Integer practiceId) {
        return timeTableService.findDiary4Practice(practiceId);
    }

    private ResponseEntity<List<TimetableNode>> saveOrUpdateTimeTable(
            final List<TimetableNode> nodes) {
        try {
            return new ResponseEntity<>(timeTableService.saveOrUpdateTimetable(nodes), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Problem occured: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
