package Jericalla2.springBootDemo;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import Jericalla2.springBootDemo.Entities.Category;
import Jericalla2.springBootDemo.Entities.Product;
import Jericalla2.springBootDemo.Repository.ProductRepository;
import Jericalla2.springBootDemo.service.ProductService;
import Jericalla2.springBootDemo.service.ProductServiceImplement;

@SpringBootTest
public class ProductServiceMockTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setUp(){
       MockitoAnnotations.initMocks(this);

       productService = new ProductServiceImplement(productRepository);
       Product computer = Product.builder()
       .id(1l)
       .name("Computer")
       .category(Category.builder().id(1L).build())
       .price(Double.parseDouble("12.5"))
       .stock((Double.parseDouble("15")))
       .build();

       Mockito.when(productRepository.findById((1L)))
       .thenReturn((Optional.of(computer)));
    }


    @Test
    public void whenValidGetID_thenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("Computer");
    }

}
