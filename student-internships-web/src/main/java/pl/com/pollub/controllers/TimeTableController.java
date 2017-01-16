package pl.com.pollub.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.pollub.db.entities.TimetableNode;
import pl.com.pollub.practice.timetable.TimeTableService;
import pl.com.pollub.practice.timetable.TimeTableType;

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

    @RequestMapping(value = "/saveSchedule", method = RequestMethod.POST)
    public ResponseEntity<List<TimetableNode>> saveSchedule(@RequestBody final List<TimetableNode> scheduleNodes) {
        return saveOrUpdateTimeTable(scheduleNodes, TimeTableType.SCHEDULE);
    }

    @RequestMapping(value = "/editSchedule", method = RequestMethod.PUT)
    public ResponseEntity<List<TimetableNode>> editSchedule(@RequestBody final List<TimetableNode> scheduleNodes) {
        return saveOrUpdateTimeTable(scheduleNodes, TimeTableType.SCHEDULE);
    }

    @RequestMapping(value = "/deleteSchedule", method = RequestMethod.DELETE)
    public void deleteSchedule(final List<TimetableNode> scheduleNodes) {
        timeTableService.deleteSchedule(scheduleNodes);
    }

    @RequestMapping(value = "/findDiary/{id}", method = RequestMethod.GET)
    public List<TimetableNode> findDiary(@PathVariable("id") final Integer practiceId) {
        return timeTableService.findDiary4Practice(practiceId);
    }

    @RequestMapping(value = "/saveDiary", method = RequestMethod.POST)
    public ResponseEntity<List<TimetableNode>> saveDiary(@RequestBody final List<TimetableNode> diaryNodes) {
        return saveOrUpdateTimeTable(diaryNodes, TimeTableType.DIARY);
    }

    @RequestMapping(value = "/editDiary", method = RequestMethod.PUT)
    public ResponseEntity<List<TimetableNode>> editDiary(@RequestBody final List<TimetableNode> scheduleNodes) {
        return saveOrUpdateTimeTable(scheduleNodes, TimeTableType.DIARY);
    }

    @RequestMapping(value = "/deleteDiary", method = RequestMethod.DELETE)
    public void deleteDiary(final List<TimetableNode> diaryNodes) {
        timeTableService.deleteDiary(diaryNodes);
    }

    private ResponseEntity<List<TimetableNode>> saveOrUpdateTimeTable(
            final List<TimetableNode> nodes, final TimeTableType timeTableType) {
        try {
            switch (timeTableType) {
                case SCHEDULE:
                    return new ResponseEntity<>(timeTableService.saveOrUpdateSchedule(nodes), HttpStatus.OK);
                case DIARY:
                    return new ResponseEntity<>(timeTableService.saveOrUpdateDiary(nodes), HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Problem occured: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
