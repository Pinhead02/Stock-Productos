package ar.edu.udecy.web.inventory.controller.impl;

    import ar.edu.udecy.web.inventory.controller.InventoryMovementController;
    import ar.edu.udecy.web.inventory.dto.InventoryMovementDTO;
    import ar.edu.udecy.web.inventory.service.InventoryMovementService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/inventory-movement")
    public class InventoryMovementControllerImpl implements InventoryMovementController {

        @Autowired
        private InventoryMovementService inventoryMovementService;

        @Override
        @GetMapping
        public List<InventoryMovementDTO> getAllInventoryMovements() {
            return inventoryMovementService.findAll();
        }

        @Override
        @GetMapping("/{movementId}")
        public InventoryMovementDTO getInventoryMovementById(@PathVariable String movementId) {
            return inventoryMovementService.findById(movementId);
        }

        @Override
        @PostMapping
        public InventoryMovementDTO createInventoryMovement(@RequestBody InventoryMovementDTO inventoryMovementDTO) {
            return inventoryMovementService.save(inventoryMovementDTO);
        }

        @Override
        @PutMapping("/{movementId}")
        public InventoryMovementDTO updateInventoryMovement(@PathVariable String movementId, @RequestBody InventoryMovementDTO inventoryMovementDTO) {
            return inventoryMovementService.update(movementId, inventoryMovementDTO);
        }

        @Override
        @DeleteMapping("/{movementId}")
        public void deleteInventoryMovement(@PathVariable String movementId) {
            inventoryMovementService.deleteById(movementId);
        }
    }