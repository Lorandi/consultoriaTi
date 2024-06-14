package com.consultoriaTi.gestao.repository;

import com.consultoriaTi.gestao.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByClientId(Long clientId);
}
