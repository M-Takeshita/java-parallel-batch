package org.batch.model.repository;

import org.batch.model.entity.TSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TSaleRepository extends JpaRepository<TSale, String> {
}