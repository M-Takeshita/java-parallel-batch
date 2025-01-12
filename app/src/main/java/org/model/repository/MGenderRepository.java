package org.model.repository;

import org.model.entity.MGender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MGenderRepository extends JpaRepository<MGender, Integer> {
}
