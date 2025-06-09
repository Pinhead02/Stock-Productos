package ar.edu.udecy.web.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictorStockDTO {
    private Long id;
    private Date date;
    private String productId;
    private Integer unitsSold;
    private BigDecimal avgSalePrice;
    private boolean promotionActive;
    private String specialEvent;

}