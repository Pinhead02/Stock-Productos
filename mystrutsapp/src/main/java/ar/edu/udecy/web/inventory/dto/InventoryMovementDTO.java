package ar.edu.udecy.web.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovementDTO {
    private String movementId;
    private Timestamp date;
    private String productId;
    private String movementType;
    private int quantity;
    private String orderId;
    private String notes;

}