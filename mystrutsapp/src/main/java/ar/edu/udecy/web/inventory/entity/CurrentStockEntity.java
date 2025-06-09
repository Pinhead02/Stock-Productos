package ar.edu.udecy.web.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "current_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentStockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id" )
    private String productId;

    @Column(name = "quantity" )
    private Integer quantity;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "total_inventory_cost")
    private Double totalInventoryCost;
}