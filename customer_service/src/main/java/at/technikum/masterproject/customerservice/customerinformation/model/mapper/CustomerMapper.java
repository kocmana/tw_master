package at.technikum.masterproject.customerservice.customerinformation.model.mapper;

import at.technikum.masterproject.customerservice.customerinformation.model.domain.Customer;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerRegistrationRequest;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerResponse;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  CustomerResponse toCustomerResponse(Customer customer);

  @Mapping(target = "customerId", ignore = true)
  Customer toCustomer(CustomerRegistrationRequest customerRegistrationRequest);

  Customer toCustomer(CustomerUpdateRequest customerUpdateRequest);

}
