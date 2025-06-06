package ar.edu.udecy.web.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentStockDTO {
    private String productId;
    private int quantity;
    private Timestamp lastUpdated;
    private BigDecimal totalInventoryCost;
}