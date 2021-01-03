package at.technikum.masterproject.monolithicservice.resolver.query.entity;

import static java.util.stream.Collectors.toUnmodifiableList;

import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerResponse;
import at.technikum.masterproject.customerservice.customernetwork.CustomerNetworkService;
import at.technikum.masterproject.customerservice.customernetwork.model.dto.CustomerNetworkResponse;
import at.technikum.masterproject.customerservice.customernetwork.model.mapper.CustomerRelationshipMapper;
import at.technikum.masterproject.ecommerceservice.purchase.PurchaseService;
import at.technikum.masterproject.ecommerceservice.purchase.model.dto.PurchaseResponse;
import at.technikum.masterproject.ecommerceservice.purchase.model.mapper.PurchaseMapper;
import at.technikum.masterproject.productservice.productreview.ProductReviewService;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewResponse;
import at.technikum.masterproject.productservice.productreview.model.mapper.ProductReviewMapper;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerResolver implements GraphQLResolver<CustomerResponse> {

  private final ProductReviewMapper productReviewMapper;
  private final ProductReviewService productReviewService;
  private final CustomerRelationshipMapper customerRelationshipMapper;
  private final CustomerNetworkService customerNetworkService;
  private final PurchaseMapper purchaseMapper;
  private final PurchaseService purchaseService;

  public List<ProductReviewResponse> getReviews(CustomerResponse customer) {
    return productReviewService.getReviewsForCustomer(customer.getCustomerId()).stream()
        .map(productReviewMapper::productReviewToProductReviewResponse)
        .collect(toUnmodifiableList());
  }

  public List<CustomerNetworkResponse> getNetwork(CustomerResponse customer) {
    return customerNetworkService.getCustomerNetworksForCustomer(customer.getCustomerId()).stream()
        .map(customerRelationshipMapper::customerNetworkToCustomerNetworkResponse)
        .collect(toUnmodifiableList());
  }

  public List<PurchaseResponse> getPurchases(CustomerResponse customer) {
    return purchaseService.getPurchasesForCustomer(customer.getCustomerId()).stream()
        .map(purchaseMapper::purchaseToPurchaseResponse)
        .collect(toUnmodifiableList());
  }
}
