package ar.edu.udecy.web.inventory.repository;

import ar.edu.udecy.web.inventory.entity.CurrentStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentStockRepository extends JpaRepository<CurrentStockEntity, Long> {
    // Custom query methods can be added here if needed
}