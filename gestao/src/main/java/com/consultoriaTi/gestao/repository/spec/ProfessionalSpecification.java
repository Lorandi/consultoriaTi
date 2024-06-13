package com.consultoriaTi.gestao.repository.spec;

import com.consultoriaTi.gestao.entity.Professional;
import com.consultoriaTi.gestao.enums.ProfessionalStatusEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Builder
public class ProfessionalSpecification implements Specification<Professional> {
    @Builder.Default
    private final transient Optional<String> name = Optional.empty();
    @Builder.Default
    private final transient Optional<String> corporateEmail = Optional.empty();
    @Builder.Default
    private final transient Optional<List<ProfessionalStatusEnum>> professionalStatus = Optional.empty();
    @Builder.Default
    private final transient Optional<String> phone = Optional.empty();


    @Override
    public Predicate toPredicate(Root<Professional> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        final var predicate = new ArrayList<Predicate>();
        name.ifPresent(n -> predicate.add(builder.like(builder.lower(root.get("name")), "%" + n.toLowerCase() + "%")));
        corporateEmail.ifPresent(ce -> predicate.add(builder.like(builder.lower(root.get("corporateEmail")), ce.toLowerCase())));
        professionalStatus.ifPresent(ps -> predicate.add(root.get("professionalStatus").in(ps)));
        phone.ifPresent(p -> predicate.add(builder.like(builder.lower(root.get("phone")), "%" + p.toLowerCase() + "%")));


        criteriaQuery.distinct(true);
        return builder.and(predicate.toArray(new Predicate[0]));
    }
}
