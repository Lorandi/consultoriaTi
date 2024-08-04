package com.consultoriaTi.gestao.repository;

import com.consultoriaTi.gestao.entity.Deallocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeallocationRepository extends JpaRepository<Deallocation, Long> {
}