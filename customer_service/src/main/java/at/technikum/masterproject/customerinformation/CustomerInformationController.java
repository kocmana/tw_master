package at.technikum.masterproject.customerinformation;

import at.technikum.masterproject.customerinformation.model.Customer;
import at.technikum.masterproject.customerinformation.model.dto.CustomerDto;
import at.technikum.masterproject.customerinformation.model.mapper.CustomerMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
class CustomerInformationController {

  private final CustomerInformationService customerInformationService;
  private final CustomerMapper customerMapper;

  @Autowired
  CustomerInformationController(CustomerInformationService customerInformationService, CustomerMapper customerMapper) {
    this.customerInformationService = customerInformationService;
    this.customerMapper = customerMapper;
  }

  @GetMapping
  public ResponseEntity<List<CustomerDto>> getAllCustomers() {
    List<Customer> allCustomers = customerInformationService.retrieveAllCustomers();
    List<CustomerDto> customerDtos = allCustomers.stream()
        .map(customerMapper::customerToCustomerDto)
        .collect(Collectors.toUnmodifiableList());
    return ResponseEntity.ok(customerDtos);
  }

  @GetMapping(path = "/{customerId}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer customerId) {
    Customer customer = customerInformationService.retrieveCustomerById(customerId);
    CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
    return ResponseEntity.ok(customerDto);
  }

  @PostMapping
  public ResponseEntity<CustomerDto> saveCustomer(@RequestBody @Valid CustomerDto customerDto) {
    customerDto.setCustomerId(null);
    Customer customer = customerMapper.customerDtoToCustomer(customerDto);
    Customer savedCustomer = customerInformationService.saveCustomer(customer);
    customerDto = customerMapper.customerToCustomerDto(savedCustomer);
    return ResponseEntity.ok(customerDto);
  }

  @PatchMapping
  public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
    Customer customer = customerMapper.customerDtoToCustomer(customerDto);
    Customer savedCustomer = customerInformationService.updateCustomer(customer);
    customerDto = customerMapper.customerToCustomerDto(savedCustomer);
    return ResponseEntity.ok(customerDto);
  }

}
