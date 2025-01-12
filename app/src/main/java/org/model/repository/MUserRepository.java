package org.model.repository;

import org.model.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MUserRepository extends JpaRepository<MUser, String> {
}