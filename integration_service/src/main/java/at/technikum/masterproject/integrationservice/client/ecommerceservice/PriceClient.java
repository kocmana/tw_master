package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PriceClient {

  Mono<Price> getCurrentPriceForProduct(int productId);

  Flux<Price> getAllPricesForProduct(int productId);

  Mono<Price> savePrice(Price price);
}
