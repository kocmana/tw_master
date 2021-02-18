package at.technikum.masterproject.customerservice.customerinformation.model.mapper;

import at.technikum.masterproject.customerservice.customerinformation.model.domain.Customer;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerRegistrationRequest;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerResponse;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerUpdateRequest;
import at.technikum.masterproject.customerservice.customerinformation.model.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  CustomerResponse toCustomerResponse(Customer customer);

  Customer toCustomer(CustomerRegistrationRequest customerRegistrationRequest);

  Customer toCustomer(CustomerUpdateRequest customerUpdateRequest);

  CustomerEntity toCustomerEntity(Customer customer);

  Customer toCustomer(CustomerEntity customerEntity);

}
