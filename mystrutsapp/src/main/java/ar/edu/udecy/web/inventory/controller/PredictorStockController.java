package ar.edu.udecy.web.inventory.controller;

import ar.edu.udecy.web.inventory.dto.PredictorStockDTO;

import java.util.List;

public interface PredictorStockController {
    List<PredictorStockDTO> getAllProducts();
    PredictorStockDTO getPredictorStockById(Long id);
    PredictorStockDTO createPredictorStock(PredictorStockDTO predictorStockDTO);
    PredictorStockDTO updatePredictorStock(Long id, PredictorStockDTO predictorStockDTO);
    void deletePredictorStock(Long id);
}