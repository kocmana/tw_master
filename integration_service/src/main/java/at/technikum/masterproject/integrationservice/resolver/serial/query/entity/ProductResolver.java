package at.technikum.masterproject.integrationservice.resolver.serial.query.entity;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PriceClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@Slf4j
public class ProductResolver implements GraphQLResolver<Product> {

  private final ProductReviewClient productReviewClient;
  private final PriceClient priceClient;

  @Autowired
  public ProductResolver(ProductReviewClient productReviewClient,
      PriceClient priceClient) {
    this.productReviewClient = productReviewClient;
    this.priceClient = priceClient;
  }

  public List<ProductReview> getReviews(Product product) {
    log.info("Retrieving all product reviews for product {} query", product.getName());
    return productReviewClient.getAllProductReviewsForProduct(product.getId());
  }

  public Price getPrice(Product product) {
    log.info("Retrieving current price for product {} query", product.getName());
    return priceClient.getCurrentPriceForProduct(product.getId());
  }

  public List<Price> getPrices(Product product) {
    log.info("Retrieving all prices for product {} query", product.getName());
    return priceClient.getAllPricesForProduct(product.getId());
  }

}
