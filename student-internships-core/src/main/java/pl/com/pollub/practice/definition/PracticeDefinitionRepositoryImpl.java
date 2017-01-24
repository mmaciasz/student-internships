package pl.com.pollub.practice.definition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pollub.db.entities.PracticeDefinition;

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
@Repository(value = PracticeDefinitionRepository.BEAN_ID)
public class PracticeDefinitionRepositoryImpl extends SimpleJpaRepository<PracticeDefinition, Integer> implements PracticeDefinitionRepository {

    private EntityManager entityManager;

    @Autowired
    public PracticeDefinitionRepositoryImpl(EntityManager entityManager) {
        super(PracticeDefinition.class, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public List<PracticeDefinition> searchByCriteria(PracticeDefSearchCriteria criteria) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PracticeDefinition> criteriaQuery = builder.createQuery(PracticeDefinition.class);
        Root<PracticeDefinition> root = criteriaQuery.from(PracticeDefinition.class);
        criteriaQuery.select(root);

        List<Predicate> conditions = new ArrayList<>();

        if(Objects.nonNull(criteria.getStatus())) {
            Predicate condition = builder.equal(root.get("status"), criteria.getStatus());
            conditions.add(condition);
        }

        if(Objects.nonNull(criteria.getFirmId())) {
            Predicate condition = builder.equal(root.get("firm").get("firmId"), criteria.getFirmId());
            conditions.add(condition);
        }

        if(!conditions.isEmpty()) {
            criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));
        }
        TypedQuery<PracticeDefinition> typedQuery = entityManager.createQuery(criteriaQuery);
        List<PracticeDefinition> practiceDefinitions = typedQuery.getResultList();

        return practiceDefinitions;
    }
}
