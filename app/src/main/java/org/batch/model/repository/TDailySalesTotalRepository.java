package org.batch.model.repository;

import org.batch.model.entity.TDailySalesTotal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TDailySalesTotalRepository extends JpaRepository<TDailySalesTotal, String> {
}