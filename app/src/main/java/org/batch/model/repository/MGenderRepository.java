package org.batch.model.repository;

import org.batch.model.entity.MGender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MGenderRepository extends JpaRepository<MGender, Integer> {
}
