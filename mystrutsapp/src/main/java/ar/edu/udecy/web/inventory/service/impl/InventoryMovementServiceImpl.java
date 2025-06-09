package ar.edu.udecy.web.inventory.service.impl;

import ar.edu.udecy.web.inventory.config.CopyNonNullConfig;
import ar.edu.udecy.web.inventory.dto.InventoryMovementDTO;
import ar.edu.udecy.web.inventory.dto.ProductDTO;
import ar.edu.udecy.web.inventory.entity.InventoryMovementEntity;
import ar.edu.udecy.web.inventory.entity.ProductEntity;
import ar.edu.udecy.web.inventory.handler.exception.ProductAlreadyExistsException;
import ar.edu.udecy.web.inventory.handler.exception.ResourceNotFoundException;
import ar.edu.udecy.web.inventory.repository.InventoryMovementRepository;
import ar.edu.udecy.web.inventory.repository.ProductRepository;
import ar.edu.udecy.web.inventory.service.InventoryMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InventoryMovementServiceImpl implements InventoryMovementService {

    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<InventoryMovementDTO> findAll() {
        return inventoryMovementRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryMovementDTO findById(String movementId) {
        InventoryMovementEntity entity = inventoryMovementRepository.findById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory movement not found with ID: " + movementId));
        return convertToDTO(entity);
    }

    @Override
    public InventoryMovementDTO save(InventoryMovementDTO inventoryMovementDTO) {
        validateProductDTO(inventoryMovementDTO);

        if (inventoryMovementRepository.existsById(inventoryMovementDTO.getMovementId())) {
            throw new ProductAlreadyExistsException("Movement with ID " + inventoryMovementDTO.getMovementId() + " already exists");
        }

        ProductEntity product = findProductById(inventoryMovementDTO.getProductId());

        InventoryMovementEntity entity = convertToEntity(inventoryMovementDTO, product);
        InventoryMovementEntity savedEntity = inventoryMovementRepository.save(entity);

        return convertToDTO(savedEntity);
    }

    @Override
    public InventoryMovementDTO update(String movementId, InventoryMovementDTO inventoryMovementDTO) {
        InventoryMovementEntity existingInventory = inventoryMovementRepository.findById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException("Movement with ID " + movementId + " not found"));

        if (inventoryMovementDTO.getProductId() != null) {
           findProductById(inventoryMovementDTO.getProductId());
        }

        CopyNonNullConfig.copyNonNullProperties(inventoryMovementDTO, existingInventory);

        InventoryMovementEntity updatedEntity = inventoryMovementRepository.save(existingInventory);
        return convertToDTO(updatedEntity);
    }

    @Override
    public void deleteById(String movementId) {
        if (!inventoryMovementRepository.existsById(movementId)) {
            throw new ResourceNotFoundException("Inventory movement not found with ID: " + movementId);
        }
        inventoryMovementRepository.deleteById(movementId);
    }

    private InventoryMovementDTO convertToDTO(InventoryMovementEntity entity) {
        return new InventoryMovementDTO(
                entity.getMovementId(),
                entity.getDate(),
                entity.getProductId(),
                entity.getMovementType(),
                entity.getQuantity(),
                entity.getOrderId(),
                entity.getNotes()
        );
    }

    private InventoryMovementEntity convertToEntity(InventoryMovementDTO dto, ProductEntity product) {
        return InventoryMovementEntity.builder()
                .movementId(dto.getMovementId())
                .date(dto.getDate())
                .productId(product.getProductId())
                .movementType(dto.getMovementType())
                .quantity(dto.getQuantity())
                .orderId(dto.getOrderId())
                .notes(dto.getNotes())
                .build();
    }

    private ProductEntity findProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
    }

    private void validateProductDTO(InventoryMovementDTO inventoryMovementDTO) {
        if (inventoryMovementDTO.getProductId() == null) {
            throw new ResourceNotFoundException("Product information is missing in the request.");
        }
    }
}