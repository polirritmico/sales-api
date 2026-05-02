package cl.duoc.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String orderReference;

    @Column(nullable = false)
    private Integer amount;

    // TODO: Implement SaleStatus
    // @Column(nullable = false)
    // @Enumerated(EnumType.IDENTITY)
    // private SaleStatus status;

    // TODO: Implement PaymentMethod
    // @Column(nullable = false)
    // @Enumerated(EnumType.IDENTITY)
    // private PaymentMethod paymentMethod;

    // NOTE: Maybe an id from the PaymentMicroservice response
    @Column
    private String transactionId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;
}
