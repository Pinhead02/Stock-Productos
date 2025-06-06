package ar.edu.udecy.web.inventory.service.impl;

import ar.edu.udecy.web.inventory.dto.ProductDTO;
import ar.edu.udecy.web.inventory.entity.ProductEntity;
import ar.edu.udecy.web.inventory.repository.ProductRepository;
import ar.edu.udecy.web.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(entity -> new ProductDTO(
                        entity.getProductId(),
                        entity.getProductName(),
                        entity.getSku(),
                        entity.getUnitOfMeasure(),
                        entity.getCost(),
                        entity.getSalePrice(),
                        entity.getCategory(),
                        entity.getLocation(),
                        entity.isActive()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(String productId) {
        ProductEntity entity = productRepository.findById(productId).orElse(null);
        return entity != null ? new ProductDTO(
                entity.getProductId(),
                entity.getProductName(),
                entity.getSku(),
                entity.getUnitOfMeasure(),
                entity.getCost(),
                entity.getSalePrice(),
                entity.getCategory(),
                entity.getLocation(),
                entity.isActive()
        ) : null;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity entity = new ProductEntity();
        entity.setProductId(productDTO.getProductId());
        entity.setProductName(productDTO.getProductName());
        entity.setSku(productDTO.getSku());
        entity.setUnitOfMeasure(productDTO.getUnitOfMeasure());
        entity.setCost(productDTO.getCost());
        entity.setSalePrice(productDTO.getSalePrice());
        entity.setCategory(productDTO.getCategory());
        entity.setLocation(productDTO.getLocation());
        entity.setActive(productDTO.isActive());
        ProductEntity savedEntity = productRepository.save(entity);
        return new ProductDTO(
                savedEntity.getProductId(),
                savedEntity.getProductName(),
                savedEntity.getSku(),
                savedEntity.getUnitOfMeasure(),
                savedEntity.getCost(),
                savedEntity.getSalePrice(),
                savedEntity.getCategory(),
                savedEntity.getLocation(),
                savedEntity.isActive()
        );
    }

    @Override
    public ProductDTO update(String productId, ProductDTO productDTO) {
        if (productRepository.existsById(productId)) {
            ProductEntity entity = new ProductEntity();
            entity.setProductId(productId);
            entity.setProductName(productDTO.getProductName());
            entity.setSku(productDTO.getSku());
            entity.setUnitOfMeasure(productDTO.getUnitOfMeasure());
            entity.setCost(productDTO.getCost());
            entity.setSalePrice(productDTO.getSalePrice());
            entity.setCategory(productDTO.getCategory());
            entity.setLocation(productDTO.getLocation());
            entity.setActive(productDTO.isActive());
            ProductEntity updatedEntity = productRepository.save(entity);
            return new ProductDTO(
                    updatedEntity.getProductId(),
                    updatedEntity.getProductName(),
                    updatedEntity.getSku(),
                    updatedEntity.getUnitOfMeasure(),
                    updatedEntity.getCost(),
                    updatedEntity.getSalePrice(),
                    updatedEntity.getCategory(),
                    updatedEntity.getLocation(),
                    updatedEntity.isActive()
            );
        }
        return null;
    }

    @Override
    public void deleteById(String productId) {
        productRepository.deleteById(productId);
    }
}