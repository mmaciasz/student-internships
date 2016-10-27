package pl.com.pollub.db.entities;

import pl.com.pollub.practice.PracticeStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Maciek on 2016-10-23.
 */
@Entity
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDate startDt;

    @Column(nullable = false)
    private LocalDate stopDt;

    private BigDecimal note;

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

    public LocalDate getStartDt() {
        return startDt;
    }

    public void setStartDt(LocalDate startDt) {
        this.startDt = startDt;
    }

    public LocalDate getStopDt() {
        return stopDt;
    }

    public void setStopDt(LocalDate stopDt) {
        this.stopDt = stopDt;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }

    public PracticeStatus getStatus() {
        return status;
    }

    public void setStatus(PracticeStatus status) {
        this.status = status;
    }
}
