package ar.edu.udecy.web.inventory.service.impl;

import ar.edu.udecy.web.inventory.dto.CurrentStockDTO;
import ar.edu.udecy.web.inventory.entity.CurrentStockEntity;
import ar.edu.udecy.web.inventory.repository.CurrentStockRepository;
import ar.edu.udecy.web.inventory.service.CurrentStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrentStockServiceImpl implements CurrentStockService {

    @Autowired
    private CurrentStockRepository currentStockRepository;

    @Override
    public List<CurrentStockDTO> findAll() {
        return currentStockRepository.findAll().stream()
                .map(entity -> new CurrentStockDTO(entity.getProductId(), entity.getQuantity(), entity.getLastUpdated(), entity.getTotalInventoryCost()))
                .collect(Collectors.toList());
    }

    @Override
    public CurrentStockDTO findById(String productId) {
        CurrentStockEntity entity = currentStockRepository.findById(productId).orElse(null);
        return entity != null ? new CurrentStockDTO(entity.getProductId(), entity.getQuantity(), entity.getLastUpdated(), entity.getTotalInventoryCost()) : null;
    }

    @Override
    public CurrentStockDTO save(CurrentStockDTO currentStockDTO) {
        CurrentStockEntity entity = new CurrentStockEntity();
        entity.setProductId(currentStockDTO.getProductId());
        entity.setQuantity(currentStockDTO.getQuantity());
        entity.setLastUpdated(currentStockDTO.getLastUpdated());
        entity.setTotalInventoryCost(currentStockDTO.getTotalInventoryCost());
        CurrentStockEntity savedEntity = currentStockRepository.save(entity);
        return new CurrentStockDTO(savedEntity.getProductId(), savedEntity.getQuantity(), savedEntity.getLastUpdated(), savedEntity.getTotalInventoryCost());
    }

    @Override
    public CurrentStockDTO update(String productId, CurrentStockDTO currentStockDTO) {
        if (currentStockRepository.existsById(productId)) {
            CurrentStockEntity entity = new CurrentStockEntity();
            entity.setProductId(productId);
            entity.setQuantity(currentStockDTO.getQuantity());
            entity.setLastUpdated(currentStockDTO.getLastUpdated());
            entity.setTotalInventoryCost(currentStockDTO.getTotalInventoryCost());
            CurrentStockEntity updatedEntity = currentStockRepository.save(entity);
            return new CurrentStockDTO(updatedEntity.getProductId(), updatedEntity.getQuantity(), updatedEntity.getLastUpdated(), updatedEntity.getTotalInventoryCost());
        }
        return null;
    }

    @Override
    public void deleteById(String productId) {
        currentStockRepository.deleteById(productId);
    }
}