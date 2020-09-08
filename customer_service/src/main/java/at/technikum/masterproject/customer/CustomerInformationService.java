package at.technikum.masterproject.customer;

import at.technikum.masterproject.customer.model.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInformationService {

  CustomerInformationRepository customerInformationRepository;

  @Autowired
  public CustomerInformationService(CustomerInformationRepository customerInformationRepository) {
    this.customerInformationRepository = customerInformationRepository;
  }

  public List<Customer> retrieveAllCustomers(){
    return customerInformationRepository.findAll();
  }
}
