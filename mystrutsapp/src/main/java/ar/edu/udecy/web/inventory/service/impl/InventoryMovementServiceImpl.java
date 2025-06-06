package ar.edu.udecy.web.inventory.service.impl;

import ar.edu.udecy.web.inventory.dto.InventoryMovementDTO;
import ar.edu.udecy.web.inventory.entity.InventoryMovementEntity;
import ar.edu.udecy.web.inventory.repository.InventoryMovementRepository;
import ar.edu.udecy.web.inventory.service.InventoryMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryMovementServiceImpl implements InventoryMovementService {

    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;

    @Override
    public List<InventoryMovementDTO> findAll() {
        return inventoryMovementRepository.findAll().stream()
                .map(entity -> new InventoryMovementDTO(
                        entity.getMovementId(),
                        entity.getDate(),
                        entity.getProductId(),
                        entity.getMovementType(),
                        entity.getQuantity(),
                        entity.getOrderId(),
                        entity.getNotes()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public InventoryMovementDTO findById(String movementId) {
        InventoryMovementEntity entity = inventoryMovementRepository.findById(movementId).orElse(null);
        return entity != null ? new InventoryMovementDTO(
                entity.getMovementId(),
                entity.getDate(),
                entity.getProductId(),
                entity.getMovementType(),
                entity.getQuantity(),
                entity.getOrderId(),
                entity.getNotes()
        ) : null;
    }

    @Override
    public InventoryMovementDTO save(InventoryMovementDTO inventoryMovementDTO) {
        InventoryMovementEntity entity = new InventoryMovementEntity();
        entity.setMovementId(inventoryMovementDTO.getMovementId());
        entity.setDate(inventoryMovementDTO.getDate());
        entity.setProductId(inventoryMovementDTO.getProductId());
        entity.setMovementType(inventoryMovementDTO.getMovementType());
        entity.setQuantity(inventoryMovementDTO.getQuantity());
        entity.setOrderId(inventoryMovementDTO.getOrderId());
        entity.setNotes(inventoryMovementDTO.getNotes());
        InventoryMovementEntity savedEntity = inventoryMovementRepository.save(entity);
        return new InventoryMovementDTO(
                savedEntity.getMovementId(),
                savedEntity.getDate(),
                savedEntity.getProductId(),
                savedEntity.getMovementType(),
                savedEntity.getQuantity(),
                savedEntity.getOrderId(),
                savedEntity.getNotes()
        );
    }

    @Override
    public InventoryMovementDTO update(String movementId, InventoryMovementDTO inventoryMovementDTO) {
        if (inventoryMovementRepository.existsById(movementId)) {
            InventoryMovementEntity entity = new InventoryMovementEntity();
            entity.setMovementId(movementId);
            entity.setDate(inventoryMovementDTO.getDate());
            entity.setProductId(inventoryMovementDTO.getProductId());
            entity.setMovementType(inventoryMovementDTO.getMovementType());
            entity.setQuantity(inventoryMovementDTO.getQuantity());
            entity.setOrderId(inventoryMovementDTO.getOrderId());
            entity.setNotes(inventoryMovementDTO.getNotes());
            InventoryMovementEntity updatedEntity = inventoryMovementRepository.save(entity);
            return new InventoryMovementDTO(
                    updatedEntity.getMovementId(),
                    updatedEntity.getDate(),
                    updatedEntity.getProductId(),
                    updatedEntity.getMovementType(),
                    updatedEntity.getQuantity(),
                    updatedEntity.getOrderId(),
                    updatedEntity.getNotes()
            );
        }
        return null;
    }

    @Override
    public void deleteById(String movementId) {
        inventoryMovementRepository.deleteById(movementId);
    }
}