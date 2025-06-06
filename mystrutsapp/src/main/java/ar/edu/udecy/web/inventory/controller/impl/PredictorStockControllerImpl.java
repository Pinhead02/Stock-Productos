package ar.edu.udecy.web.inventory.controller.impl;

import ar.edu.udecy.web.inventory.controller.PredictorStockController;
import ar.edu.udecy.web.inventory.dto.PredictorStockDTO;
import ar.edu.udecy.web.inventory.service.PredictorStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/predictor-stock")
public class PredictorStockControllerImpl implements PredictorStockController {

    @Autowired
    private PredictorStockService predictorStockService;

    @Override
    @GetMapping
    public List<PredictorStockDTO> getAllPredictorStocks() {
        return predictorStockService.findAll();
    }

    @Override
    @GetMapping("/{date}")
    public PredictorStockDTO getPredictorStockByDate(@PathVariable Date date) {
        return predictorStockService.findByDate(date);
    }

    @Override
    @PostMapping
    public PredictorStockDTO createPredictorStock(@RequestBody PredictorStockDTO predictorStockDTO) {
        return predictorStockService.save(predictorStockDTO);
    }

    @Override
    @PutMapping("/{date}")
    public PredictorStockDTO updatePredictorStock(@PathVariable Date date, @RequestBody PredictorStockDTO predictorStockDTO) {
        return predictorStockService.update(date, predictorStockDTO);
    }

    @Override
    @DeleteMapping("/{date}")
    public void deletePredictorStock(@PathVariable Date date) {
        predictorStockService.deleteByDate(date);
    }
}