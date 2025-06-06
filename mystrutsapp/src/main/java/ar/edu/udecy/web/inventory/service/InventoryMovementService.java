package ar.edu.udecy.web.inventory.service;

import ar.edu.udecy.web.inventory.dto.InventoryMovementDTO;

import java.util.List;

public interface InventoryMovementService {
    List<InventoryMovementDTO> findAll();
    InventoryMovementDTO findById(String movementId);
    InventoryMovementDTO save(InventoryMovementDTO inventoryMovementDTO);
    InventoryMovementDTO update(String movementId, InventoryMovementDTO inventoryMovementDTO);
    void deleteById(String movementId);
}