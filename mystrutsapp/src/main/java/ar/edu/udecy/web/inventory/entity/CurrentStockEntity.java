package ar.edu.udecy.web.inventory.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.math.BigDecimal;
    import java.sql.Timestamp;

    @Entity
    @Table(name = "current_stock")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class CurrentStockEntity {
        @Id
        @Column(name = "product_id", nullable = false)
        private String productId;

        @Column(name = "quantity", nullable = false)
        private int quantity;

        @Column(name = "last_updated", nullable = false)
        private Timestamp lastUpdated;

        @Column(name = "total_inventory_cost", nullable = false)
        private BigDecimal totalInventoryCost;
    }