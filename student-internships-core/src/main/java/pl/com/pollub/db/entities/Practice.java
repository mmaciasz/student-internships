package pl.com.pollub.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "practiceDefinitionId", nullable = false)
    private PracticeDefinition practiceDefinition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firmEmployeeId")
    private User firmEmployee;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promoterId")
    private User promoter;

    @Column(length = 12)
    private String academicYear;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate startDt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate stopDt;

    private BigDecimal note;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PracticeStatus status;

    public Practice() {
    }

    public Practice(Integer practiceId) {
        this.practiceId = practiceId;
    }

    public Integer getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Integer practiceId) {
        this.practiceId = practiceId;
    }

    public PracticeDefinition getPracticeDefinition() {
        return practiceDefinition;
    }

    public void setPracticeDefinition(PracticeDefinition practiceDefinition) {
        this.practiceDefinition = practiceDefinition;
    }

    public User getFirmEmployee() {
        return firmEmployee;
    }

    public void setFirmEmployee(User firmEmployee) {
        this.firmEmployee = firmEmployee;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getPromoter() {
        return promoter;
    }

    public void setPromoter(User promoter) {
        this.promoter = promoter;
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

    public boolean isWaitingForApproval() {
        return PracticeStatus.WAITING_FOR_APPROVAL.equals(status);
    }

    public boolean isApproved() {
        return PracticeStatus.APPROVED.equals(status);
    }
}
