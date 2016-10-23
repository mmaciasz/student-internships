package pl.com.pollub.db.entities;

import pl.com.pollub.practice.PracticeStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Maciek on 2016-10-23.
 */
@Entity
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer practiceId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PracticeDefinition practiceDefinitionId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User tutorId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User promoterId;

    @Column(length = 12)
    private String academicYear;

    @Column(nullable = false)
    private Date startDt;

    @Column(nullable = false)
    private Date stopDt;

    private Double note;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PracticeStatus status;

    public Practice() {
    }

    public Integer getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Integer practiceId) {
        this.practiceId = practiceId;
    }

    public PracticeDefinition getPracticeDefinitionId() {
        return practiceDefinitionId;
    }

    public void setPracticeDefinitionId(PracticeDefinition practiceDefinitionId) {
        this.practiceDefinitionId = practiceDefinitionId;
    }

    public User getTutorId() {
        return tutorId;
    }

    public void setTutorId(User tutorId) {
        this.tutorId = tutorId;
    }

    public User getStudentId() {
        return studentId;
    }

    public void setStudentId(User studentId) {
        this.studentId = studentId;
    }

    public User getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(User promoterId) {
        this.promoterId = promoterId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
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

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public PracticeStatus getStatus() {
        return status;
    }

    public void setStatus(PracticeStatus status) {
        this.status = status;
    }
}
