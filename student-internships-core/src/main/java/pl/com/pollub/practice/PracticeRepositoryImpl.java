package pl.com.pollub.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pollub.db.entities.Practice;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Maciek on 2016-12-10.
 */
@Repository(value = PracticeRepository.BEAN_ID)
public class PracticeRepositoryImpl extends SimpleJpaRepository<Practice, Integer> implements PracticeRepository {

    private EntityManager entityManager;

    @Autowired
    public PracticeRepositoryImpl(EntityManager entityManager) {
        super(Practice.class, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public List<Practice> searchByCriteria(PracticeSearchCriteria criteria) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Practice> criteriaQuery = builder.createQuery(Practice.class);
        Root<Practice> root = criteriaQuery.from(Practice.class);
        criteriaQuery.select(root);

        List<Predicate> conditions = new ArrayList<>();

        if(Objects.nonNull(criteria.getFirmId())) {
            Predicate condition = builder.equal(root.join("practiceDefinition").get("firm").get("firmId"), criteria.getFirmId());
            conditions.add(condition);
        }

        if(Objects.nonNull(criteria.getPracticeStatus())) {
            Predicate condition = builder.equal(root.get("status"), criteria.getPracticeStatus());
            conditions.add(condition);
        }

        if(Objects.nonNull(criteria.getStudentId())) {
            Predicate condition = builder.equal(root.join("student").get("userId"), criteria.getStudentId());
            conditions.add(condition);
        }

        if(!conditions.isEmpty()) {
            criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));
        }
        TypedQuery<Practice> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Practice> practiceDefinitions = typedQuery.getResultList();

        return practiceDefinitions;
    }
}
