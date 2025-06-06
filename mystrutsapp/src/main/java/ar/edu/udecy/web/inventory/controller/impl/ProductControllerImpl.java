package ar.edu.udecy.web.inventory.controller.impl;

import ar.edu.udecy.web.inventory.controller.ProductController;
import ar.edu.udecy.web.inventory.dto.ProductDTO;
import ar.edu.udecy.web.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }

    @Override
    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable String productId) {
        return productService.findById(productId);
    }

    @Override
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @Override
    @PutMapping("/{productId}")
    public ProductDTO updateProduct(@PathVariable String productId, @RequestBody ProductDTO productDTO) {
        return productService.update(productId, productDTO);
    }

    @Override
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteById(productId);
    }
}