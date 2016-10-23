package pl.com.pollub.db.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Maciek on 2016-10-23.
 */
@Entity
public class TimetableNode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer timetableNodeId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Practice practice;

    private Integer type;

    private Date startDt;

    private Date stopDt;

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

    public Practice getPractice() {
        return practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getStopDt() {
        return stopDt;
    }

    public void setStopDt(Date stopDt) {
        this.stopDt = stopDt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
