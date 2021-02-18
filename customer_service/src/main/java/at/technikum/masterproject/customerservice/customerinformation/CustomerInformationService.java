package at.technikum.masterproject.customerservice.customerinformation;

import at.technikum.masterproject.customerservice.customerinformation.model.CustomerNotFoundException;
import at.technikum.masterproject.customerservice.customerinformation.model.domain.Customer;
import at.technikum.masterproject.customerservice.customerinformation.model.entity.CustomerEntity;
import at.technikum.masterproject.customerservice.customerinformation.model.mapper.CustomerMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInformationService {

  private final CustomerInformationRepository customerInformationRepository;
  private final CustomerMapper customerMapper;

  @Autowired
  CustomerInformationService(CustomerInformationRepository customerInformationRepository,
                             CustomerMapper customerMapper) {
    this.customerInformationRepository = customerInformationRepository;
    this.customerMapper = customerMapper;
  }

  List<Customer> retrieveAllCustomers() {
    return customerInformationRepository.findAll().stream()
        .map(customerMapper::toCustomer)
        .collect(Collectors.toList());
  }

  public List<Customer> retrieveCustomersByIds(List<Integer> customerIds) {
    return customerInformationRepository.findAllById(customerIds).stream()
        .map(customerMapper::toCustomer)
        .collect(Collectors.toList());
  }

  public Customer retrieveCustomerById(int customerId) {
    CustomerEntity customerEntity = customerInformationRepository.findById(customerId)
        .orElseThrow(() -> generateCustomerNotFoundException(customerId));
    return customerMapper.toCustomer(customerEntity);
  }

  Customer saveCustomer(Customer customer) {
    CustomerEntity customerEntity = customerMapper.toCustomerEntity(customer);
    CustomerEntity savedCustomer = customerInformationRepository.save(customerEntity);
    return customerMapper.toCustomer(savedCustomer);
  }

  void updateCustomer(Customer customer) {
    if (customerDoesNotExist(customer.getCustomerId())) {
      throw generateCustomerNotFoundException(customer.getCustomerId());
    }
    saveCustomer(customer);
  }

  void deleteCustomer(int customerId) {
    if (customerDoesNotExist(customerId)) {
      throw generateCustomerNotFoundException(customerId);
    }
    customerInformationRepository.deleteById(customerId);
  }

  private boolean customerDoesNotExist(int customerId) {
    return !customerInformationRepository.existsById(customerId);
  }

  private CustomerNotFoundException generateCustomerNotFoundException(int customerId) {
    return new CustomerNotFoundException(customerId);
  }
}
