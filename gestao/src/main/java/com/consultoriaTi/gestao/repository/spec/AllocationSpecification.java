package com.consultoriaTi.gestao.repository.spec;

import com.consultoriaTi.gestao.entity.Allocation;
import com.consultoriaTi.gestao.entity.Professional;
import com.consultoriaTi.gestao.enums.AllocationStatusEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
public class AllocationSpecification implements Specification<Allocation> {

    private static final String PROFESSIONAL_ID = "professionalId";

    @Builder.Default
    private transient Optional<List<Long>> professionalId = Optional.empty();
    @Builder.Default
    private final transient Optional<List<Long>> clientId = Optional.empty();
    @Builder.Default
    private final transient Optional<String> role = Optional.empty();
    @Builder.Default
    private final transient Optional<BigDecimal> valuePerHour = Optional.empty();
    @Builder.Default
    private final transient Optional<LocalDate> allocationStartDate = Optional.empty();
    @Builder.Default
    private final transient Optional<LocalDate> allocationEndDate = Optional.empty();
    @Builder.Default
    private final transient Optional<List<AllocationStatusEnum>> allocationStatus = Optional.empty();
    @Builder.Default
    private final transient Optional<String> professionalName = Optional.empty();


    @Override
    public Predicate toPredicate(Root<Allocation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        final var predicates = new ArrayList<Predicate>();
        final var professional = criteriaQuery.from(Professional.class);

        professionalId.ifPresent(pi -> predicates.add(root.get(PROFESSIONAL_ID).in(pi)));
        clientId.ifPresent(ci -> predicates.add(root.get("clientId").in(ci)));
        role.ifPresent(r -> predicates.add(builder.like(builder.lower(root.get("role")), "%" + r.toLowerCase() + "%")));
        valuePerHour.ifPresent(vph -> predicates.add(root.get("valuePerHour").in(vph)));
        allocationStartDate.ifPresent(asd -> predicates.add(root.get("allocationStartDate").in(asd)));
        allocationEndDate.ifPresent(aed -> predicates.add(root.get("allocationEndDate").in(aed)));
        allocationStatus.ifPresent(as -> predicates.add(root.get("allocationStatus").in(as)));
        professionalName.ifPresent(pn -> {
            predicates.add(builder.equal(root.get(PROFESSIONAL_ID), professional.get("id")));
            predicates.add(builder.like(builder.lower(professional.get("name")), "%" + pn.toLowerCase() + "%"));
        });

        criteriaQuery.distinct(true);
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
