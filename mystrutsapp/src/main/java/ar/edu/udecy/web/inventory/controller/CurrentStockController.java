package ar.edu.udecy.web.inventory.controller;

import ar.edu.udecy.web.inventory.dto.CurrentStockDTO;

import java.util.List;

public interface CurrentStockController {
    List<CurrentStockDTO> getAllCurrentStock();
    CurrentStockDTO getCurrentStockById(String productId);
    CurrentStockDTO createCurrentStock(CurrentStockDTO currentStockDTO);
    CurrentStockDTO updateCurrentStock(String productId, CurrentStockDTO currentStockDTO);
    void deleteCurrentStock(String productId);
}