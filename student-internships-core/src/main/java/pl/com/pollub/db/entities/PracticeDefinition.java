package pl.com.pollub.db.entities;

import pl.com.pollub.practice.definition.PracticeDefinitionStatus;

import javax.persistence.*;

/**
 * Created by Maciek on 2016-10-23.
 */
@Entity
public class PracticeDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer practiceDefinitionId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "firmId", nullable = false)
    private Firm firm;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PracticeDefinitionStatus status;

    @Column(nullable = false, length = 150)
    private String name;

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

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
