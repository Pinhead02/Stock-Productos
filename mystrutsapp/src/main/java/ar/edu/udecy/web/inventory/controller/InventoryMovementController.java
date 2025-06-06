package ar.edu.udecy.web.inventory.controller;

import ar.edu.udecy.web.inventory.dto.InventoryMovementDTO;

import java.util.List;

public interface InventoryMovementController {
    List<InventoryMovementDTO> getAllInventoryMovements();
    InventoryMovementDTO getInventoryMovementById(String movementId);
    InventoryMovementDTO createInventoryMovement(InventoryMovementDTO inventoryMovementDTO);
    InventoryMovementDTO updateInventoryMovement(String movementId, InventoryMovementDTO inventoryMovementDTO);
    void deleteInventoryMovement(String movementId);
}