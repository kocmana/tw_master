package at.technikum.masterproject.monolithicservice.resolver.query;

import static java.util.stream.Collectors.toList;

import at.technikum.masterproject.productservice.productinformation.ProductInformationService;
import at.technikum.masterproject.productservice.productinformation.model.Product;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductResponse;
import at.technikum.masterproject.productservice.productinformation.model.mapper.ProductMapper;
import at.technikum.masterproject.productservice.productreview.ProductReviewService;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewResponse;
import at.technikum.masterproject.productservice.productreview.model.mapper.ProductReviewMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductQueryResolver implements GraphQLQueryResolver {

  private final ProductMapper productMapper;
  private final ProductInformationService productInformationService;
  private final ProductReviewMapper productReviewMapper;
  private final ProductReviewService productReviewService;

  public List<ProductResponse> products() {
    log.info("Retrieving all products query");
    return productInformationService.retrieveAllProducts().stream()
        .map(productMapper::productToProductResponse)
        .collect(toList());
  }

  public ProductResponse product(int id) {
    log.info("Retrieved product query for id {}", id);
    Product product = productInformationService.retrieveProductById(id);
    return productMapper.productToProductResponse(product);
  }

  public List<ProductReviewResponse> reviews() {
    log.info("Retrieving all product reviews query");
    return productReviewService.getAllReviews(null).stream()
        .map(productReviewMapper::productReviewToProductReviewResponse)
        .collect(toList());
  }

}
