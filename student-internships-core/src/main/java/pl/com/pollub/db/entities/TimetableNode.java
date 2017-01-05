package pl.com.pollub.db.entities;

import pl.com.pollub.practice.timetable.TimeTableType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Maciek on 2016-10-23.
 */
@Entity
public class TimetableNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timetableNodeId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Practice practiceId;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TimeTableType type;

    private LocalDateTime startDt;

    private LocalDateTime stopDt;

    @Column(columnDefinition = "TEXT")
    private String description;

    public TimetableNode() {
    }

    public Integer getTimetableNodeId() {
        return timetableNodeId;
    }

    public void setTimetableNodeId(Integer timetableNodeId) {
        this.timetableNodeId = timetableNodeId;
    }

    public Practice getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Practice practiceId) {
        this.practiceId = practiceId;
    }

    public TimeTableType getType() {
        return type;
    }

    public void setType(TimeTableType type) {
        this.type = type;
    }

    public LocalDateTime getStartDt() {
        return startDt;
    }

    public void setStartDt(LocalDateTime startDt) {
        this.startDt = startDt;
    }

    public LocalDateTime getStopDt() {
        return stopDt;
    }

    public void setStopDt(LocalDateTime stopDt) {
        this.stopDt = stopDt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
