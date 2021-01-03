package at.technikum.masterproject.monolithicservice.resolver.query;

import static java.util.stream.Collectors.toList;

import at.technikum.masterproject.customerservice.customerinformation.CustomerInformationService;
import at.technikum.masterproject.customerservice.customerinformation.model.Customer;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerResponse;
import at.technikum.masterproject.customerservice.customerinformation.model.mapper.CustomerMapper;
import at.technikum.masterproject.customerservice.customernetwork.CustomerNetworkService;
import at.technikum.masterproject.customerservice.customernetwork.model.CustomerNetwork;
import at.technikum.masterproject.customerservice.customernetwork.model.mapper.CustomerRelationshipMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerQueryResolver implements GraphQLQueryResolver {

  private final CustomerMapper customerMapper;
  private final CustomerInformationService customerInformationService;
  private final CustomerRelationshipMapper customerRelationshipMapper;
  private final CustomerNetworkService customerNetworkService;

  public List<CustomerResponse> customers() {
    log.info("Retrieving all customers query");
    return customerInformationService.retrieveAllCustomers().stream()
        .map(customerMapper::customerToCustomerResponse)
        .collect(toList());
  }

  public CustomerResponse customer(int customerId) {
    log.info("Retrieved customer query for customerId {}", customerId);
    Customer customer = customerInformationService.retrieveCustomerById(customerId);
    return customerMapper.customerToCustomerResponse(customer);
  }

  public List<CustomerNetwork> customerNetwork(int customerId) {
    log.info("Retrieved customer network query for customerId {}", customerId);
    return customerNetworkService.getCustomerNetworksForCustomer(customerId);
  }

}
