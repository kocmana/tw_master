package at.technikum.masterproject.monolithicservice.resolver.mutation;

import at.technikum.masterproject.customerservice.customerinformation.CustomerInformationService;
import at.technikum.masterproject.customerservice.customerinformation.model.Customer;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerRegistrationRequest;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerUpdateRequest;
import at.technikum.masterproject.customerservice.customerinformation.model.mapper.CustomerMapper;
import at.technikum.masterproject.customerservice.customernetwork.CustomerNetworkService;
import at.technikum.masterproject.customerservice.customernetwork.model.CustomerInteraction;
import at.technikum.masterproject.customerservice.customernetwork.model.dto.CustomerInteractionDto;
import at.technikum.masterproject.customerservice.customernetwork.model.mapper.CustomerRelationshipMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMutationResolver implements GraphQLMutationResolver {

  private final CustomerMapper customerMapper;
  private final CustomerInformationService customerInformationService;
  private final CustomerRelationshipMapper customerRelationshipMapper;
  private final CustomerNetworkService customerNetworkService;

  public int createCustomer(CustomerRegistrationRequest customerDto) {
    Customer customer = customerMapper.customerRegistrationRequestToCustomer(customerDto);
    return customerInformationService.saveCustomer(customer)
        .getCustomerId();
  }

  public void updateCustomer(CustomerUpdateRequest customerDto) {
    Customer customer = customerMapper.customerUpdateRequestToCustomer(customerDto);
    customerInformationService.updateCustomer(customer);
  }

  public void deleteCustomer(int customerId) {
    customerInformationService.deleteCustomer(customerId);
  }

  public CustomerInteractionDto createCustomerInteraction(
      CustomerInteractionDto customerInteractionDto) {
    CustomerInteraction customerInteraction =
        customerRelationshipMapper.customerInteractionDtoToCustomerInteraction(customerInteractionDto);
    customerInteraction = customerNetworkService.saveCustomerRelationship(customerInteraction);
    return customerRelationshipMapper.customerInteractionToCustomerInteractionDto(customerInteraction);
  }
}
