package at.technikum.masterproject.customerinformation;

import at.technikum.masterproject.customerinformation.model.Customer;
import at.technikum.masterproject.customerinformation.model.CustomerNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInformationService {

  private final CustomerInformationRepository customerInformationRepository;

  @Autowired
  CustomerInformationService(CustomerInformationRepository customerInformationRepository) {
    this.customerInformationRepository = customerInformationRepository;
  }

  List<Customer> retrieveAllCustomers() {
    return customerInformationRepository.findAll();
  }

  public List<Customer> retrieveCustomersByIds(List<Integer> customerIds) {
    return customerInformationRepository.findAllById(customerIds);
  }

  public Customer retrieveCustomerById(int customerId) {
    return customerInformationRepository.findById(customerId).orElseThrow(
        () -> generateCustomerNotFoundException(customerId));
  }

  Customer saveCustomer(Customer customer) {
    return customerInformationRepository.save(customer);
  }

  Customer updateCustomer(Customer customer) {
    if (!customerDoesNotExist(customer.getCustomerId())){
      throw generateCustomerNotFoundException(customer.getCustomerId());
    }
    return customerInformationRepository.save(customer);
  }

  private boolean customerDoesNotExist(int customerId){
    return customerInformationRepository.existsById(customerId);
  }

  private CustomerNotFoundException generateCustomerNotFoundException(int customerId) {
    return new CustomerNotFoundException(customerId);
  }
}
