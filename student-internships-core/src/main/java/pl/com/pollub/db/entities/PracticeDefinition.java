package pl.com.pollub.db.entities;

import pl.com.pollub.practice.PracticeDefinitionStatus;

import javax.persistence.*;

/**
 * Created by Maciek on 2016-10-23.
 */
@Entity
public class PracticeDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer practiceDefinitionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Firm firmId;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PracticeDefinitionStatus status;

    @Column(nullable = false, length = 500)
    private String description;

    public PracticeDefinition() {
    }

    public Integer getPracticeDefinitionId() {
        return practiceDefinitionId;
    }

    public void setPracticeDefinitionId(Integer practiceDefinitionId) {
        this.practiceDefinitionId = practiceDefinitionId;
    }

    public Firm getFirmId() {
        return firmId;
    }

    public void setFirmId(Firm firmId) {
        this.firmId = firmId;
    }

    public PracticeDefinitionStatus getStatus() {
        return status;
    }

    public void setStatus(PracticeDefinitionStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
