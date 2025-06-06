package ar.edu.udecy.web.inventory.service;

import ar.edu.udecy.web.inventory.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(String productId);
    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(String productId, ProductDTO productDTO);
    void deleteById(String productId);
}