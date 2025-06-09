package ar.edu.udecy.web.inventory.service.impl;

import ar.edu.udecy.web.inventory.config.CopyNonNullConfig;
import ar.edu.udecy.web.inventory.dto.CurrentStockDTO;
import ar.edu.udecy.web.inventory.entity.CurrentStockEntity;
import ar.edu.udecy.web.inventory.entity.ProductEntity;
import ar.edu.udecy.web.inventory.repository.CurrentStockRepository;
import ar.edu.udecy.web.inventory.repository.ProductRepository;
import ar.edu.udecy.web.inventory.service.CurrentStockService;
import ar.edu.udecy.web.inventory.handler.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CurrentStockServiceImpl implements CurrentStockService {

    @Autowired
    private CurrentStockRepository currentStockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<CurrentStockDTO> findAll() {
        return currentStockRepository.findAll().stream()
                .map(entity -> new CurrentStockDTO(
                        entity.getId(),
                        entity.getProductId(),
                        entity.getQuantity(),
                        entity.getLastUpdated(),
                        entity.getTotalInventoryCost()))
                .collect(Collectors.toList());
    }

    @Override
    public CurrentStockDTO findById(Long productId) {
        CurrentStockEntity entity = currentStockRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Current stock not found for product ID: " + productId));
        return new CurrentStockDTO(
                entity.getId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getLastUpdated(),
                entity.getTotalInventoryCost());
    }

    @Override
    public CurrentStockDTO save(CurrentStockDTO currentStockDTO) {
        if (currentStockDTO.getId() != null && currentStockRepository.existsById(currentStockDTO.getId())) {
            throw new ResourceNotFoundException("Current stock already exists with ID: " + currentStockDTO.getId());
        }

        ProductEntity product = productRepository.findById(currentStockDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + currentStockDTO.getProductId()));

        CurrentStockEntity entity = new CurrentStockEntity();
        entity.setProductId(product.getProductId());
        entity.setQuantity(currentStockDTO.getQuantity());
        entity.setLastUpdated(LocalDateTime.now());
        entity.setTotalInventoryCost(currentStockDTO.getTotalInventoryCost());

        CurrentStockEntity savedEntity = currentStockRepository.save(entity);

        return new CurrentStockDTO(
                savedEntity.getId(),
                savedEntity.getProductId(),
                savedEntity.getQuantity(),
                savedEntity.getLastUpdated(),
                savedEntity.getTotalInventoryCost());
    }

    @Override
    public CurrentStockDTO update(Long productId, CurrentStockDTO currentStockDTO) {
        CurrentStockEntity entity = currentStockRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Current stock not found for product ID: " + productId));

        if (Objects.nonNull(currentStockDTO.getProductId())) {
             productRepository.findById(currentStockDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
        }
        CopyNonNullConfig.copyNonNullProperties(currentStockDTO, entity);

        CurrentStockEntity updatedEntity = currentStockRepository.save(entity);
        return new CurrentStockDTO(
                updatedEntity.getId(),
                updatedEntity.getProductId(),
                updatedEntity.getQuantity(),
                updatedEntity.getLastUpdated(),
                updatedEntity.getTotalInventoryCost());
    }

    @Override
    public void deleteById(Long productId) {
        CurrentStockEntity entity = currentStockRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Current stock not found for product ID: " + productId));
        currentStockRepository.delete(entity);
    }
}