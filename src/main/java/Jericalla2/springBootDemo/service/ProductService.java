package Jericalla2.springBootDemo.service;

import java.util.List;

import Jericalla2.springBootDemo.Entities.Category;
import Jericalla2.springBootDemo.Entities.Product;

public interface ProductService {
    
    public List<Product> listAllProduct();

    public Product getProduct(Long Id);

    public Product createProduct(Product product);

    public Product updateProduct(Product product);

    public Product deleteProduct(Long id);

    public List<Product> findByCategory(Category category);

    public Product updateStock(Long id, Double quantity);

}
