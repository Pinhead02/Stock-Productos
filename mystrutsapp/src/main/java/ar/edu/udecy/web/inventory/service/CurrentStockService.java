package ar.edu.udecy.web.inventory.service;

import ar.edu.udecy.web.inventory.dto.CurrentStockDTO;

import java.util.List;

public interface CurrentStockService {
    List<CurrentStockDTO> findAll();
    CurrentStockDTO findById(String id);
    CurrentStockDTO save(CurrentStockDTO currentStockDTO);
    CurrentStockDTO update(String id, CurrentStockDTO currentStockDTO);
    void deleteById(String id);
}