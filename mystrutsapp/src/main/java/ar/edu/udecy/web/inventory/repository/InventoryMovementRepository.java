package ar.edu.udecy.web.inventory.repository;

import ar.edu.udecy.web.inventory.entity.InventoryMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovementEntity, String> {
    // Custom query methods can be added here if needed
}