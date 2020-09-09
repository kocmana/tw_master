package at.technikum.masterproject.customerinformation.model.mapper;

import at.technikum.masterproject.customerinformation.model.Customer;
import at.technikum.masterproject.customerinformation.model.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  CustomerDto customerToCustomerDto(Customer customer);

  Customer customerDtoToCustomer(CustomerDto customerDto);
}
