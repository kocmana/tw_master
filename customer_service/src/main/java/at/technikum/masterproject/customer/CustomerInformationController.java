package at.technikum.masterproject.customer;

import at.technikum.masterproject.customer.model.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerInformationController {

  CustomerInformationService customerInformationService;

  @Autowired
  public CustomerInformationController(CustomerInformationService customerInformationService) {
    this.customerInformationService = customerInformationService;
  }

  @GetMapping
  public ResponseEntity<List<Customer>> getAllCustomers(){
    List<Customer> allCustomers = customerInformationService.retrieveAllCustomers();
    return ResponseEntity.ok(allCustomers);
  }

}
