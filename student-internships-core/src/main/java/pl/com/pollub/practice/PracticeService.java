package pl.com.pollub.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.com.pollub.db.entities.Practice;
import pl.com.pollub.db.entities.PracticeDefinition;
import pl.com.pollub.exception.ValidationException;
import pl.com.pollub.practice.definition.PracticeDefSearchCriteria;
import pl.com.pollub.practice.definition.PracticeDefinitionRepository;
import pl.com.pollub.util.AcademicYearResolver;

import java.util.List;
import java.util.Objects;

/**
 * Created by Maciek on 2016-12-08.
 */
@Service
public class PracticeService {



    @Autowired
    @Qualifier(value = PracticeDefinitionRepository.BEAN_ID)
    private PracticeDefinitionRepository practiceDefRepository;

    @Autowired
    @Qualifier(value = PracticeRepository.BEAN_ID)
    private PracticeRepository practiceRepository;

    public List<PracticeDefinition> searchPracticeDefinitions(PracticeDefSearchCriteria criteria) {

        return practiceDefRepository.searchByCriteria(criteria);
    }

    public List<Practice> searchPractices(PracticeSearchCriteria criteria) {

        return practiceRepository.searchByCriteria(criteria);
    }

    public PracticeDefinition findPracticeDefinitionById(Integer id) {

        return practiceDefRepository.findOne(id);
    }

    public Practice findPracticeById(Integer id) {

        return practiceRepository.findOne(id);
    }

    public void removePracticeDefinition(Integer id) {
        practiceDefRepository.delete(id);
    }

    public PracticeDefinition saveOrUpdatePracticeDefinition(PracticeDefinition practiceDefinition) {
        return practiceDefRepository.save(practiceDefinition);
    }

    public Practice saveOrUpdatePractice(Practice practice) {

        if(!validatePractice(practice)) {
            throw new ValidationException();
        }

        String academicYear = AcademicYearResolver.computeAcademicYear(practice.getStartDt());
        practice.setAcademicYear(academicYear);

        return practiceRepository.save(practice);
    }

    private boolean validatePractice(Practice practice) {

        if(Objects.isNull(practice.getStartDt()) || Objects.isNull(practice.getStopDt())) {
            return false;
        }

        if(practice.getStartDt().isAfter(practice.getStopDt())) {
            return false;
        }

        return true;
    }

}
