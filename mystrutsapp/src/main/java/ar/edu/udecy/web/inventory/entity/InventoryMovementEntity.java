package ar.edu.udecy.web.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "inventory_movement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovementEntity {
    @Id
    @Column(name = "movement_id", nullable = false)
    private String movementId;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "movement_type", nullable = false)
    private String movementType;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "notes")
    private String notes;
}