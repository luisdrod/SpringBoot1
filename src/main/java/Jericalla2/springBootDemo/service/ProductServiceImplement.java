package Jericalla2.springBootDemo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Jericalla2.springBootDemo.Entities.Category;
import Jericalla2.springBootDemo.Entities.Product;
import Jericalla2.springBootDemo.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImplement implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long Id) {
        return productRepository.findById(Id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("Created");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDb = getProduct(product.getId());
        if (productDb == null)
            return null;
        productDb.setName(product.getName());
        productDb.setDescription(product.getDescription());
        productDb.setCategory(product.getCategory());
        productDb.setPrice(product.getPrice()); 
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDb = getProduct(id);
        if (productDb == null)
            return null;
        productDb.setStatus("Deleted");
        return productRepository.save(productDb);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {

        Product productDb = getProduct(id);
        if (productDb == null)
            return null;
        productDb.setStock(quantity);
        return productRepository.save(productDb);
    }

    public ProductServiceImplement(ProductRepository productRepository2) {
        this.productRepository = productRepository2;
    }
    
}
