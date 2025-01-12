package org.batch.model.repository;

import org.batch.model.entity.MProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MProductRepository extends JpaRepository<MProduct, String> {
}