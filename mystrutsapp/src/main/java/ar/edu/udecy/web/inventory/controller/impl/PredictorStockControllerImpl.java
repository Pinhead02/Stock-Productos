package ar.edu.udecy.web.inventory.controller.impl;

import ar.edu.udecy.web.inventory.controller.PredictorStockController;
import ar.edu.udecy.web.inventory.dto.PredictorStockDTO;
import ar.edu.udecy.web.inventory.service.PredictorStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predictor-stocks")
public class PredictorStockControllerImpl implements PredictorStockController {

    @Autowired
    private PredictorStockService predictorStockService;

    @GetMapping
    public List<PredictorStockDTO> getAllProducts() {
        return predictorStockService.findAll();

    }

    @GetMapping("/{id}")
    public PredictorStockDTO getPredictorStockById(@PathVariable Long id) {
        return predictorStockService.findById(id);
    }

    @PostMapping
    public PredictorStockDTO createPredictorStock(@RequestBody PredictorStockDTO predictorStockDTO) {
        return predictorStockService.save(predictorStockDTO);

    }

    @PutMapping("/{id}")
    public PredictorStockDTO updatePredictorStock(
            @PathVariable Long id, @RequestBody PredictorStockDTO predictorStockDTO) {
        return predictorStockService.update(id, predictorStockDTO);

    }

    @DeleteMapping("/{id}")
    public void deletePredictorStock(@PathVariable Long id) {
        predictorStockService.deleteById(id);
    }
}