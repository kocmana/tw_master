package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePriceInput;
import java.util.List;
import reactor.core.publisher.Mono;

public interface PriceClient {

  Mono<Price> getCurrentPriceForProduct(int productId);

  Mono<List<Price>> getAllPricesForProduct(int productId);

  Mono<Price> savePrice(CreatePriceInput price);
}
