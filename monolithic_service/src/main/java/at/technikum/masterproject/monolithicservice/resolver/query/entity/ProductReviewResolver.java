package at.technikum.masterproject.monolithicservice.resolver.query.entity;

import at.technikum.masterproject.customerservice.customerinformation.CustomerInformationService;
import at.technikum.masterproject.customerservice.customerinformation.model.Customer;
import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerResponse;
import at.technikum.masterproject.customerservice.customerinformation.model.mapper.CustomerMapper;
import at.technikum.masterproject.productservice.productinformation.ProductInformationService;
import at.technikum.masterproject.productservice.productinformation.model.Product;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductResponse;
import at.technikum.masterproject.productservice.productinformation.model.mapper.ProductMapper;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewResponse;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductReviewResolver implements GraphQLResolver<ProductReviewResponse> {

  private final ProductMapper productMapper;
  private final ProductInformationService productInformationService;
  private final CustomerMapper customerMapper;
  private final CustomerInformationService customerInformationService;

  public ProductResponse getProduct(ProductReviewResponse productReview) {
    Product product = productInformationService.retrieveProductById(productReview.getId());
    return productMapper.productToProductResponse(product);
  }

  public CustomerResponse getCustomer(ProductReviewResponse productReview) {
    Customer customer = customerInformationService.retrieveCustomerById(productReview.getId());
    return customerMapper.customerToCustomerResponse(customer);
  }

}
