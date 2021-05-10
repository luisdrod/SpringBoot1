package Jericalla2.springBootDemo;

import java.util.*;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Jericalla2.springBootDemo.Entities.Category;
import Jericalla2.springBootDemo.Entities.Product;
import Jericalla2.springBootDemo.Repository.ProductRepository;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
	private ProductRepository productRespositoy;
	
	@Test
	public void whenFindByCategory_thenRetunrResult() {
		Product p1 = Product.builder()
				.name("computer")
				.category(Category.builder().id(1L).build())
				.description("")
				.stock(Double.parseDouble("10"))
				.price(Double.parseDouble("120.5"))
				.status("Created")
				.createAt(new Date())
				.build();

			productRespositoy.save(p1);

			List<Product> founds = productRespositoy.findByCategory(p1.getCategory());
			Assertions.assertTrue(founds.size()>0); 
		
	}
}
