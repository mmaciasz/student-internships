package pl.com.pollub.practice.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.com.pollub.db.entities.Practice;
import pl.com.pollub.db.entities.TimetableNode;
import pl.com.pollub.practice.PracticeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 09.01.17.
 */
@Service
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final PracticeRepository practiceRepository;

    @Autowired
    public TimeTableService(TimeTableRepository timeTableRepository, PracticeRepository practiceRepository) {
        this.timeTableRepository = timeTableRepository;
        this.practiceRepository = practiceRepository;
    }

    public List<TimetableNode> findSchedule4Practice(final Integer practiceId) {
        final Practice practice = practiceRepository.findOne(practiceId);
        final List<TimetableNode> nodes = timeTableRepository.findByPracticeIdAndTypeOrderByTimetableNodeIdAsc(practice, TimeTableType.SCHEDULE);
        if (CollectionUtils.isEmpty(nodes)) {
            nodes.addAll(createNewTimetable(practice, practice.getStartDt(), practice.getStopDt(), TimeTableType.SCHEDULE));
        }
        return nodes;
    }

    public List<TimetableNode> findDiary4Practice(final Integer practiceId) {
        final Practice practice = practiceRepository.findOne(practiceId);
        final List<TimetableNode> nodes = timeTableRepository.findByPracticeIdAndTypeOrderByTimetableNodeIdAsc(practice, TimeTableType.DIARY);
        if (CollectionUtils.isEmpty(nodes)) {
            nodes.addAll(createNewTimetable(practice, practice.getStartDt(), practice.getStopDt(), TimeTableType.DIARY));
        }
        return nodes;
    }

    public List<TimetableNode> saveOrUpdateTimetable(final List<TimetableNode> nodes) {
        final List<TimetableNode> ret = new ArrayList<>();
        nodes.forEach(node -> node.setType(TimeTableType.SCHEDULE));
        timeTableRepository.save(nodes).forEach(ret::add);
        return ret;
    }

    private List<TimetableNode> createNewTimetable(Practice practice, LocalDate startDt, LocalDate stopDt, TimeTableType type) {
        final List<TimetableNode> nodes = new ArrayList<>();
        while (!startDt.isAfter(stopDt)) {
            nodes.add(new TimetableNode(practice, type, startDt.atTime(7, 0), startDt.atTime(15, 0)));
            startDt = startDt.plusDays(1L);
        }
        return nodes;
    }
}
