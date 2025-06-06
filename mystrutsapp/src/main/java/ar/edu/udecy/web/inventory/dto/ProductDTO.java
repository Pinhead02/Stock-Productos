package ar.edu.udecy.web.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String productId;
    private String productName;
    private String sku;
    private String unitOfMeasure;
    private BigDecimal cost;
    private BigDecimal salePrice;
    private String category;
    private String location;
    private boolean active;

}