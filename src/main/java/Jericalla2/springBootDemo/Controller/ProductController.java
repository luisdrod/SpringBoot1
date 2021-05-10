package Jericalla2.springBootDemo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Jericalla2.springBootDemo.Entities.Category;
import Jericalla2.springBootDemo.Entities.Product;
import Jericalla2.springBootDemo.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam (name="categoryId", required = false) Long categoryId){
        List<Product> products;
        if (categoryId == null)
            products = productService.listAllProduct();
        else{
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty())
                return ResponseEntity.notFound().build();
        }

        if (products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if (null == product)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        if (result.hasErrors())
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.FormatMessage(result));
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        product.setId(id);
        Product productDb = productService.updateProduct(product);
        if (productDb == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(productDb);
    }

    @DeleteMapping( value="/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product productToDelete = productService.deleteProduct(id);
        if (productToDelete == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productToDelete);
    }

    @GetMapping("{id}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable("id") Long id, @RequestParam(name = "quantity",  required = true) Double Stock){
        Product product = productService.updateStock(id, Stock);
        if (product == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    private String FormatMessage( BindingResult result )
    {
        List<Map<String,String>> errors = result.getFieldErrors().stream()
        .map( err -> {
             Map<String,String> error = new HashMap<>();
             error.put(err.getField(), err.getDefaultMessage());
             return error;
        }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
        .code("01").messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try{
             jsonString = mapper.writeValueAsString(errorMessage);
        }catch(JsonProcessingException ex){
             ex.printStackTrace();
        }
         return jsonString;

    }
}
