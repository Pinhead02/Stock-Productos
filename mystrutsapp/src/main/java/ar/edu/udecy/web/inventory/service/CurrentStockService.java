package ar.edu.udecy.web.inventory.service;

import ar.edu.udecy.web.inventory.dto.CurrentStockDTO;

import java.util.List;

public interface CurrentStockService {
    List<CurrentStockDTO> findAll();
    CurrentStockDTO findById(Long id);
    CurrentStockDTO save(CurrentStockDTO currentStockDTO);
    CurrentStockDTO update(Long id, CurrentStockDTO currentStockDTO);
    void deleteById(Long id);
}