package ar.edu.udecy.web.inventory.repository;

import ar.edu.udecy.web.inventory.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    // Custom query methods can be added here if needed
}