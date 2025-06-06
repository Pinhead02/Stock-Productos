package ar.edu.udecy.web.inventory.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.math.BigDecimal;

    @Entity
    @Table(name = "product")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ProductEntity {
        @Id
        @Column(name = "product_id", nullable = false)
        private String productId;

        @Column(name = "product_name", nullable = false)
        private String productName;

        @Column(name = "sku", nullable = false)
        private String sku;

        @Column(name = "unit_of_measure", nullable = false)
        private String unitOfMeasure;

        @Column(name = "cost", nullable = false)
        private BigDecimal cost;

        @Column(name = "sale_price", nullable = false)
        private BigDecimal salePrice;

        @Column(name = "category", nullable = false)
        private String category;

        @Column(name = "location", nullable = false)
        private String location;

        @Column(name = "active", nullable = false)
        private boolean active;
    }