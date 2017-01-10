package pl.com.pollub.practice.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pollub.db.entities.TimetableNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 09.01.17.
 */
@Service
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;

    @Autowired
    public TimeTableService(TimeTableRepository timeTableRepository) {
        this.timeTableRepository = timeTableRepository;
    }

    public List<TimetableNode> findSchedule4Practice(final Integer practiceId) {
        return timeTableRepository.findByPracticeIdAndType(practiceId, TimeTableType.SCHEDULE.name());
    }

    public List<TimetableNode> findDiary4Practice(final Integer practiceId) {
        return timeTableRepository.findByPracticeIdAndType(practiceId, TimeTableType.DIARY.name());
    }

    public List<TimetableNode> saveOrUpdateSchedule(final List<TimetableNode> scheduleNodes) {
        final List<TimetableNode> ret = new ArrayList<>();
        scheduleNodes.forEach(node -> node.setType(TimeTableType.SCHEDULE));
        timeTableRepository.save(scheduleNodes).forEach(ret::add);
        return ret;
    }

    public void deleteSchedule(final List<TimetableNode> scheduleNodes) {
        scheduleNodes.forEach(node -> node.setType(TimeTableType.SCHEDULE));
        timeTableRepository.delete(scheduleNodes);
    }

    public List<TimetableNode> saveOrUpdateDiary(final List<TimetableNode> scheduleNodes) {
        final List<TimetableNode> ret = new ArrayList<>();
        scheduleNodes.forEach(node -> node.setType(TimeTableType.DIARY));
        timeTableRepository.save(scheduleNodes).forEach(ret::add);
        return ret;
    }

    public void deleteDiary(final List<TimetableNode> scheduleNodes) {
        scheduleNodes.forEach(node -> node.setType(TimeTableType.DIARY));
        timeTableRepository.delete(scheduleNodes);
    }
}
