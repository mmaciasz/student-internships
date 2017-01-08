package pl.com.pollub.practice.definition;

/**
 * Created by Maciek on 2016-12-08.
 */
public class PracticeDefSearchCriteria {

    private String searchStr;

    private PracticeDefinitionStatus status;

    private Integer firmId;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public PracticeDefinitionStatus getStatus() {
        return status;
    }

    public void setStatus(PracticeDefinitionStatus status) {
        this.status = status;
    }

    public Integer getFirmId() {
        return firmId;
    }

    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }
}
