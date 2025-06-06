package ar.edu.udecy.web.inventory.service;

import ar.edu.udecy.web.inventory.dto.PredictorStockDTO;

import java.sql.Date;
import java.util.List;

public interface PredictorStockService {
    List<PredictorStockDTO> findAll();
    PredictorStockDTO findByDate(Date date);
    PredictorStockDTO save(PredictorStockDTO predictorStockDTO);
    PredictorStockDTO update(Date date, PredictorStockDTO predictorStockDTO);
    void deleteByDate(Date date);
}