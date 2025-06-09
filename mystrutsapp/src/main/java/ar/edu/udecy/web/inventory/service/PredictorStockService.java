package ar.edu.udecy.web.inventory.service;

import ar.edu.udecy.web.inventory.dto.PredictorStockDTO;

import java.sql.Date;
import java.util.List;

public interface PredictorStockService {
    List<PredictorStockDTO> findAll();
    PredictorStockDTO findById(Long id);
    PredictorStockDTO save(PredictorStockDTO predictorStockDTO);
    PredictorStockDTO update(Long id, PredictorStockDTO predictorStockDTO);
    void deleteById(Long id);
}