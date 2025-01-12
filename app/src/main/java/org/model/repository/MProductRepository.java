package org.model.repository;

import org.model.entity.MProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MProductRepository extends JpaRepository<MProduct, String> {
}