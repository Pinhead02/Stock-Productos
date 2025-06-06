package ar.edu.udecy.web.inventory.controller.impl;

import ar.edu.udecy.web.inventory.controller.CurrentStockController;
import ar.edu.udecy.web.inventory.dto.CurrentStockDTO;
import ar.edu.udecy.web.inventory.service.CurrentStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/current-stock")
public class CurrentStockControllerImpl implements CurrentStockController {

    @Autowired
    private CurrentStockService currentStockService;

    @Override
    @GetMapping
    public List<CurrentStockDTO> getAllCurrentStock() {
        return currentStockService.findAll();
    }

    @Override
    @GetMapping("/{productId}")
    public CurrentStockDTO getCurrentStockById(@PathVariable String productId) {
        return currentStockService.findById(productId);
    }

    @Override
    @PostMapping
    public CurrentStockDTO createCurrentStock(@RequestBody CurrentStockDTO currentStockDTO) {
        return currentStockService.save(currentStockDTO);
    }

    @Override
    @PutMapping("/{productId}")
    public CurrentStockDTO updateCurrentStock(@PathVariable String productId, @RequestBody CurrentStockDTO currentStockDTO) {
        return currentStockService.update(productId, currentStockDTO);
    }

    @Override
    @DeleteMapping("/{productId}")
    public void deleteCurrentStock(@PathVariable String productId) {
        currentStockService.deleteById(productId);
    }
}