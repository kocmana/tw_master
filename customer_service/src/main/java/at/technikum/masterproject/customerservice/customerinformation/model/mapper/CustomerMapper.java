package at.technikum.masterproject.customerservice.customerinformation.model.mapper;

import at.technikum.masterproject.customerservice.customerinformation.model.Customer;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerRegistrationRequest;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerResponse;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerUpdateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  CustomerResponse customerToCustomerResponse(Customer customer);

  Customer customerRegistrationRequestToCustomer(CustomerRegistrationRequest customerRegistrationRequest);

  Customer customerUpdateRequestToCustomer(CustomerUpdateRequest customerUpdateRequest);

}
