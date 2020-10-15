package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.UpdateCustomerInput;
import java.util.List;

public interface CustomerInformationClient {

  Customer getCustomerById(int id);

  List<Customer> getAllCustomer();

   int saveCustomer(CreateCustomerInput customer);

  void updateCustomer(UpdateCustomerInput customer);

  void deleteCustomer(int customerId);
}
