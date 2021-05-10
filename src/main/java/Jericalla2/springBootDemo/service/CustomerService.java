package Jericalla2.springBootDemo.service;

import java.util.List;

import Jericalla2.springBootDemo.Entities.Customer;
import Jericalla2.springBootDemo.Entities.Region;

public interface CustomerService {

    public List<Customer> findCustomerAll();
    public List<Customer> findCustomersByRegion(Region region);

    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public  Customer getCustomer(Long id);

}
