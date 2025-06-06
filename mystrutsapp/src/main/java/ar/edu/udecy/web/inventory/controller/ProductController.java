package ar.edu.udecy.web.inventory.controller;

import ar.edu.udecy.web.inventory.dto.ProductDTO;

import java.util.List;

public interface ProductController {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(String productId);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(String productId, ProductDTO productDTO);
    void deleteProduct(String productId);
}