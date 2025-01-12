package org.batch.model.repository;

import org.batch.model.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MUserRepository extends JpaRepository<MUser, String> {
}