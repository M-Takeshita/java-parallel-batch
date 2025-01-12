package org.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "M_Users")
public class MUser {

    @Id
    @Column(columnDefinition = "VARCHAR(36) NOT NULL DEFAULT (UUID())", insertable = false, updatable = false)
    private String id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "first_name_kana", length = 50, nullable = false)
    private String firstNameKana;

    @Column(name = "last_name_kana", length = 50, nullable = false)
    private String lastNameKana;

    @Column(nullable = false)
    private int age;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private MGender gender;

    @Column(name = "created_at", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
