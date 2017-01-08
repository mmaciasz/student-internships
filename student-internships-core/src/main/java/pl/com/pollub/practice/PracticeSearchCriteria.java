package pl.com.pollub.practice;

/**
 * Created by Maciek on 2017-01-04.
 */
public class PracticeSearchCriteria {

    private Integer firmId;

    private PracticeStatus practiceStatus;

    private Integer studentId;

    public Integer getFirmId() {
        return firmId;
    }

    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }

    public PracticeStatus getPracticeStatus() {
        return practiceStatus;
    }

    public void setPracticeStatus(PracticeStatus practiceStatus) {
        this.practiceStatus = practiceStatus;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
