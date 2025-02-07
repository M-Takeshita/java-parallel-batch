package org.batch.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "T_Sales")
public class TSale {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private String id;

    @Column(name = "sale_date", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime saleDate;

    @Column(name="user_id")
    private String userId;

    @Column(name="product_id")
    private String productId;

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