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
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            @Column(name = "date")
            private Date date;

            @Column(name = "product_id")
            private String productId;

            @Column(name = "units_sold")
            private Integer unitsSold;

            @Column(name = "avg_sale_price")
            private BigDecimal avgSalePrice;

            @Column(name = "promotion_active")
            private boolean promotionActive;

            @Column(name = "special_event")
            private String specialEvent;
        }