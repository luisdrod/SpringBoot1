package Jericalla2.springBootDemo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import Jericalla2.springBootDemo.Entities.Category;
import Jericalla2.springBootDemo.Entities.Product;;

public interface ProductRepository extends JpaRepository<Product,Long> {

	public List<Product> findByCategory(Category category);
	
}
