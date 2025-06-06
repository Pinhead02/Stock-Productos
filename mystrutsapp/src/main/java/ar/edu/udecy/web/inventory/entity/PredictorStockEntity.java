package ar.edu.udecy.web.inventory.entity;

        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.math.BigDecimal;
        import java.sql.Date;

        @Entity
        @Table(name = "predictor_stock")
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class PredictorStockEntity {
            @Id
            @Column(name = "date", nullable = false)
            private Date date;

            @Column(name = "product_id", nullable = false)
            private String productId;

            @Column(name = "units_sold", nullable = false)
            private int unitsSold;

            @Column(name = "avg_sale_price", nullable = false)
            private BigDecimal avgSalePrice;

            @Column(name = "promotion_active", nullable = false)
            private boolean promotionActive;

            @Column(name = "special_event")
            private String specialEvent;
        }