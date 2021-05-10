package Jericalla2.springBootDemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Jericalla2.springBootDemo.Entities.Customer;
import Jericalla2.springBootDemo.Entities.Region;

import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer,Long> {
        public Customer findByNumberID(String numberID);
        public List<Customer> findByLastName(String lastName);
        public List<Customer> findByRegion(Region region);
}
