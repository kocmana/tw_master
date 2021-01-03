package at.technikum.masterproject.monolithicservice.resolver.query.entity;

import at.technikum.masterproject.customerservice.customerinformation.CustomerInformationService;
import at.technikum.masterproject.customerservice.customerinformation.model.Customer;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerResponse;
import at.technikum.masterproject.customerservice.customerinformation.model.mapper.CustomerMapper;
import at.technikum.masterproject.ecommerceservice.purchase.model.dto.PurchaseResponse;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PurchaseResolver implements GraphQLResolver<PurchaseResponse> {

  private final CustomerMapper customerMapper;
  private final CustomerInformationService customerInformationService;

  public CustomerResponse getCustomer(PurchaseResponse purchase) {
    Customer customer = customerInformationService.retrieveCustomerById(purchase.getCustomerId());
    return customerMapper.customerToCustomerResponse(customer);
  }
}
