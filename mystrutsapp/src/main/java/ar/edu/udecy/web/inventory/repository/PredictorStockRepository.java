package ar.edu.udecy.web.inventory.repository;

import ar.edu.udecy.web.inventory.entity.PredictorStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictorStockRepository extends JpaRepository<PredictorStockEntity, Long> {
    // Custom query methods can be added here if needed
}