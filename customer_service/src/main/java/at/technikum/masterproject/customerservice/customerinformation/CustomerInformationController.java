package at.technikum.masterproject.customerservice.customerinformation;

import static java.util.stream.Collectors.toUnmodifiableList;

import at.technikum.masterproject.commons.delay.annotation.FixedEndpointDelaySimulation;
import at.technikum.masterproject.customerservice.customerinformation.model.Customer;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerRegistrationRequest;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerResponse;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerUpdateRequest;
import at.technikum.masterproject.customerservice.customerinformation.model.mapper.CustomerMapper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  @FixedEndpointDelaySimulation(delayInMs = 100)
  public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
    List<Customer> allCustomers = customerInformationService.retrieveAllCustomers();
    List<CustomerResponse> customerResponses = allCustomers.stream()
        .map(customerMapper::customerToCustomerResponse)
        .collect(toUnmodifiableList());
    return ResponseEntity.ok(customerResponses);
  }

  @GetMapping(path = "/{customerId}")
  public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable int customerId) {
    Customer customer = customerInformationService.retrieveCustomerById(customerId);
    CustomerResponse customerResponse = customerMapper.customerToCustomerResponse(customer);
    return ResponseEntity.ok(customerResponse);
  }

  @PostMapping
  public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody @Valid CustomerRegistrationRequest customerRegistrationRequest) {
    Customer customer = customerMapper.customerRegistrationRequestToCustomer(customerRegistrationRequest);
    Customer savedCustomer = customerInformationService.saveCustomer(customer);
    CustomerResponse customerResponse = customerMapper.customerToCustomerResponse(savedCustomer);
    return ResponseEntity.ok(customerResponse);
  }

  @PutMapping
  public void updateCustomer(@RequestBody @Valid CustomerUpdateRequest customerUpdateRequest) {
    Customer customer = customerMapper.customerUpdateRequestToCustomer(customerUpdateRequest);
    customerInformationService.updateCustomer(customer);
  }

  @DeleteMapping(path = "/{customerId}")
  public void deleteCustomer(@PathVariable int customerId) {
    customerInformationService.deleteCustomer(customerId);
  }

}
