package com.consultoriaTi.client.repository.spec;

import com.consultoriaTi.client.entity.Client;

import com.consultoriaTi.client.enums.ClientStatusEnum;
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
public class ClientSpecification implements Specification<Client> {
    @Builder.Default
    private final transient Optional<String> name = Optional.empty();
    @Builder.Default
    private final transient Optional<String> phone = Optional.empty();
    @Builder.Default
    private final transient Optional<String> contactEmail = Optional.empty();
    @Builder.Default
    private final transient Optional<List<ClientStatusEnum>> clientStatus = Optional.empty();

    @Override
    public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        final var predicate = new ArrayList<Predicate>();
        name.ifPresent(n -> predicate.add(builder.like(builder.lower(root.get("name")), "%" + n.toLowerCase() + "%")));
        phone.ifPresent(p -> predicate.add(builder.like(builder.lower(root.get("phone")), "%" + p.toLowerCase() + "%")));
        contactEmail.ifPresent(ce -> predicate.add(builder.like(builder.lower(root.get("contactEmail")), ce.toLowerCase())));
        clientStatus.ifPresent(ps -> predicate.add(root.get("clientStatus").in(ps)));

        criteriaQuery.distinct(true);
        return builder.and(predicate.toArray(new Predicate[0]));
    }
}
