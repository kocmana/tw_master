package at.technikum.masterproject.monolithicservice.resolver.query.entity;

import static java.util.stream.Collectors.toUnmodifiableList;

import at.technikum.masterproject.ecommerceservice.price.PriceService;
import at.technikum.masterproject.ecommerceservice.price.model.Price;
import at.technikum.masterproject.ecommerceservice.price.model.dto.PriceDto;
import at.technikum.masterproject.ecommerceservice.price.model.mapper.PriceMapper;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductResponse;
import at.technikum.masterproject.productservice.productreview.ProductReviewService;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewResponse;
import at.technikum.masterproject.productservice.productreview.model.mapper.ProductReviewMapper;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductResolver implements GraphQLResolver<ProductResponse> {

  private final ProductReviewMapper productReviewMapper;
  private final ProductReviewService productReviewService;
  private final PriceMapper priceMapper;
  private final PriceService priceService;

  public List<ProductReviewResponse> getReviews(ProductResponse product) {
    log.info("Retrieving all product reviews for product {} query", product.getName());
    return productReviewService.getReviewsForProduct(product.getId()).stream()
        .map(productReviewMapper::productReviewToProductReviewResponse)
        .collect(toUnmodifiableList());
  }

  public PriceDto getPrice(ProductResponse product) {
    log.info("Retrieving current price for product {} query", product.getName());
    Price price = priceService.getCurrentPriceForProduct(product.getId());
    return priceMapper.priceToPriceDto(price);
  }

  public List<PriceDto> getPrices(ProductResponse product) {
    log.info("Retrieving all prices for product {} query", product.getName());
    return priceService.getAllPricesForProduct(product.getId()).stream()
        .map(priceMapper::priceToPriceDto)
        .collect(toUnmodifiableList());
  }

}
