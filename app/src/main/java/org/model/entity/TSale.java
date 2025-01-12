package org.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "T_Sales")
public class TSale {

    @Id
    @Column(columnDefinition = "VARCHAR(36) NOT NULL DEFAULT (UUID())", insertable = false, updatable = false)
    private String id;

    @Column(name = "sale_date", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime saleDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MUser user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private MProduct product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal unitAmount;

    @Column(name = "total_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "tax_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal taxAmount;

    @Column(name = "payment_method_id", nullable = false)
    private Integer paymentMethodId;

    @Column(length = 20, nullable = false)
    private String status;

    @Column(name = "store_id", nullable = false, columnDefinition = "INT(1) NOT NULL DEFAULT 1")
    private Integer storeId;

    @Column(name = "created_at", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}