package at.technikum.masterproject.customerservice.customerinformation;

import static java.util.stream.Collectors.toUnmodifiableList;

import at.technikum.masterproject.customerservice.customerinformation.model.domain.Customer;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerRegistrationRequest;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerResponse;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerUpdateRequest;
import at.technikum.masterproject.customerservice.customerinformation.model.mapper.CustomerMapper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
class CustomerInformationController {

  private final CustomerInformationService customerInformationService;
  private final CustomerMapper customerMapper;

  @Autowired
  CustomerInformationController(CustomerInformationService customerInformationService,
                                CustomerMapper customerMapper) {
    this.customerInformationService = customerInformationService;
    this.customerMapper = customerMapper;
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
    List<Customer> allCustomers = customerInformationService.retrieveAllCustomers();
    List<CustomerResponse> customerResponses = allCustomers.stream()
        .map(customerMapper::toCustomerResponse)
        .collect(toUnmodifiableList());
    return ResponseEntity.ok(customerResponses);
  }

  @GetMapping(path = "/{customerId}")
  public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable int customerId) {
    Customer customer = customerInformationService.retrieveCustomerById(customerId);
    CustomerResponse customerResponse = customerMapper.toCustomerResponse(customer);
    return ResponseEntity.ok(customerResponse);
  }

  @PostMapping
  public ResponseEntity<CustomerResponse> saveCustomer(
      @RequestBody @Valid CustomerRegistrationRequest customerRegistrationRequest) {
    Customer customer = customerMapper.toCustomer(customerRegistrationRequest);
    Customer savedCustomer = customerInformationService.saveCustomer(customer);
    CustomerResponse customerResponse = customerMapper.toCustomerResponse(savedCustomer);
    return ResponseEntity.ok(customerResponse);
  }

  @PutMapping
  public void updateCustomer(@RequestBody @Valid CustomerUpdateRequest customerUpdateRequest) {
    Customer customer = customerMapper.toCustomer(customerUpdateRequest);
    customerInformationService.updateCustomer(customer);
  }

  @DeleteMapping(path = "/{customerId}")
  public void deleteCustomer(@PathVariable int customerId) {
    customerInformationService.deleteCustomer(customerId);
  }

}
