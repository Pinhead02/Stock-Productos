package ar.edu.udecy.web.inventory.service.impl;

import ar.edu.udecy.web.inventory.dto.PredictorStockDTO;
import ar.edu.udecy.web.inventory.entity.PredictorStockEntity;
import ar.edu.udecy.web.inventory.repository.PredictorStockRepository;
import ar.edu.udecy.web.inventory.service.PredictorStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PredictorStockServiceImpl implements PredictorStockService {

    @Autowired
    private PredictorStockRepository predictorStockRepository;

    @Override
    public List<PredictorStockDTO> findAll() {
        return predictorStockRepository.findAll().stream()
                .map(entity -> new PredictorStockDTO(
                        entity.getDate(),
                        entity.getProductId(),
                        entity.getUnitsSold(),
                        entity.getAvgSalePrice(),
                        entity.isPromotionActive(),
                        entity.getSpecialEvent()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public PredictorStockDTO findByDate(Date date) {
        PredictorStockEntity entity = predictorStockRepository.findById(date).orElse(null);
        return entity != null ? new PredictorStockDTO(
                entity.getDate(),
                entity.getProductId(),
                entity.getUnitsSold(),
                entity.getAvgSalePrice(),
                entity.isPromotionActive(),
                entity.getSpecialEvent()
        ) : null;
    }

    @Override
    public PredictorStockDTO save(PredictorStockDTO predictorStockDTO) {
        PredictorStockEntity entity = new PredictorStockEntity();
        entity.setDate(predictorStockDTO.getDate());
        entity.setProductId(predictorStockDTO.getProductId());
        entity.setUnitsSold(predictorStockDTO.getUnitsSold());
        entity.setAvgSalePrice(predictorStockDTO.getAvgSalePrice());
        entity.setPromotionActive(predictorStockDTO.isPromotionActive());
        entity.setSpecialEvent(predictorStockDTO.getSpecialEvent());
        PredictorStockEntity savedEntity = predictorStockRepository.save(entity);
        return new PredictorStockDTO(
                savedEntity.getDate(),
                savedEntity.getProductId(),
                savedEntity.getUnitsSold(),
                savedEntity.getAvgSalePrice(),
                savedEntity.isPromotionActive(),
                savedEntity.getSpecialEvent()
        );
    }

    @Override
    public PredictorStockDTO update(Date date, PredictorStockDTO predictorStockDTO) {
        if (predictorStockRepository.existsById(date)) {
            PredictorStockEntity entity = new PredictorStockEntity();
            entity.setDate(date);
            entity.setProductId(predictorStockDTO.getProductId());
            entity.setUnitsSold(predictorStockDTO.getUnitsSold());
            entity.setAvgSalePrice(predictorStockDTO.getAvgSalePrice());
            entity.setPromotionActive(predictorStockDTO.isPromotionActive());
            entity.setSpecialEvent(predictorStockDTO.getSpecialEvent());
            PredictorStockEntity updatedEntity = predictorStockRepository.save(entity);
            return new PredictorStockDTO(
                    updatedEntity.getDate(),
                    updatedEntity.getProductId(),
                    updatedEntity.getUnitsSold(),
                    updatedEntity.getAvgSalePrice(),
                    updatedEntity.isPromotionActive(),
                    updatedEntity.getSpecialEvent()
            );
        }
        return null;
    }

    @Override
    public void deleteByDate(Date date) {
        predictorStockRepository.deleteById(date);
    }
}