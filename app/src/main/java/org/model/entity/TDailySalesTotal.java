package org.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "T_DailySalesTotals")
public class TDailySalesTotal {

    @Id
    @Column(columnDefinition = "VARCHAR(36) NOT NULL DEFAULT (UUID())", insertable = false, updatable = false)
    private String id;

    @Column(name = "sale_date", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime saleDate;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private MProduct product;

    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    @Column(name = "total_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "total_tax_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalTaxAmount;

    @Column(name = "created_at", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}