package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePriceInput;
import java.util.List;

public interface PriceClient {

  Price getCurrentPriceForProduct(int productId);

  List<Price> getAllPricesForProduct(int productId);

  Price savePrice(CreatePriceInput price);
}
