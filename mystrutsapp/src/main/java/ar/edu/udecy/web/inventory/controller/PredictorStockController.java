package ar.edu.udecy.web.inventory.controller;

import ar.edu.udecy.web.inventory.dto.PredictorStockDTO;

import java.util.List;

public interface PredictorStockController {
    List<PredictorStockDTO> getAllPredictorStocks();
    PredictorStockDTO getPredictorStockByDate(java.sql.Date date);
    PredictorStockDTO createPredictorStock(PredictorStockDTO predictorStockDTO);
    PredictorStockDTO updatePredictorStock(java.sql.Date date, PredictorStockDTO predictorStockDTO);
    void deletePredictorStock(java.sql.Date date);
}