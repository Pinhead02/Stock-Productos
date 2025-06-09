package ar.edu.udecy.web.inventory.service.impl;

import ar.edu.udecy.web.inventory.config.CopyNonNullConfig;
import ar.edu.udecy.web.inventory.dto.PredictorStockDTO;
import ar.edu.udecy.web.inventory.entity.InventoryMovementEntity;
import ar.edu.udecy.web.inventory.entity.PredictorStockEntity;
import ar.edu.udecy.web.inventory.entity.ProductEntity;
import ar.edu.udecy.web.inventory.handler.exception.ProductAlreadyExistsException;
import ar.edu.udecy.web.inventory.handler.exception.ResourceNotFoundException;
import ar.edu.udecy.web.inventory.repository.PredictorStockRepository;
import ar.edu.udecy.web.inventory.repository.ProductRepository;
import ar.edu.udecy.web.inventory.service.PredictorStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PredictorStockProductServiceImpl implements PredictorStockService {

    @Autowired
    private PredictorStockRepository predictorStockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<PredictorStockDTO> findAll() {
        return predictorStockRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PredictorStockDTO findById(Long id) {
        return predictorStockRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("PredictorStock with ID " + id + " not found"));
    }

    @Override
    public PredictorStockDTO save(PredictorStockDTO predictorStockDTO) {
        if (predictorStockRepository.existsById(predictorStockDTO.getId())) {
            throw new ProductAlreadyExistsException("Predictor Stock with ID " + predictorStockDTO.getId() + " already exists");
        }

        ProductEntity product = productRepository.findById(predictorStockDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + predictorStockDTO.getProductId()));

        PredictorStockEntity entity = mapToEntity(predictorStockDTO, product);

        return mapToDTO(predictorStockRepository.save(entity));

    }

    @Override
    public PredictorStockDTO update(Long id, PredictorStockDTO predictorStockDTO) {

      PredictorStockEntity predictorStockEntity = predictorStockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Predictor StockDT with ID " + id + " not found"));

        if (predictorStockDTO.getProductId() != null) {
            productRepository.findById(predictorStockDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + predictorStockDTO.getProductId()));
        }

        CopyNonNullConfig.copyNonNullProperties(predictorStockDTO, predictorStockEntity);

        PredictorStockEntity updatedEntity = predictorStockRepository.save(predictorStockEntity);
        return mapToDTO(updatedEntity);
    }


    @Override
    public void deleteById(Long id) {
        if (!predictorStockRepository.existsById(id)) {
            throw new ResourceNotFoundException("PredictorStock with ID " + id + " not found");
        }
        try {
            predictorStockRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting PredictorStock with ID " + id + ": " + e.getMessage(), e);
        }
    }

    private PredictorStockDTO mapToDTO(PredictorStockEntity entity) {
        return new PredictorStockDTO(
                entity.getId(),
                entity.getDate(),
                entity.getProductId(),
                entity.getUnitsSold(),
                entity.getAvgSalePrice(),
                entity.isPromotionActive(),
                entity.getSpecialEvent()
        );
    }

    private PredictorStockEntity mapToEntity(PredictorStockDTO dto, ProductEntity product) {
        PredictorStockEntity entity = new PredictorStockEntity();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setProductId(product.getProductId());
        entity.setUnitsSold(dto.getUnitsSold());
        entity.setAvgSalePrice(dto.getAvgSalePrice());
        entity.setPromotionActive(dto.isPromotionActive());
        entity.setSpecialEvent(dto.getSpecialEvent());
        return entity;
    }

    private void updateEntity(PredictorStockEntity entity, PredictorStockDTO dto) {
        entity.setDate(dto.getDate());
        entity.setUnitsSold(dto.getUnitsSold());
        entity.setAvgSalePrice(dto.getAvgSalePrice());
        entity.setPromotionActive(dto.isPromotionActive());
        entity.setSpecialEvent(dto.getSpecialEvent());
    }
}