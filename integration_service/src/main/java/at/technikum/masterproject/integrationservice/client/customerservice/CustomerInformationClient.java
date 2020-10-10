package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import java.util.List;

public interface CustomerInformationClient {

  Customer getCustomerById(int id);

  List<Customer> getAllCustomer();

  Customer saveCustomer(Customer customer);

  Customer updateCustomer(Customer customer);
}
