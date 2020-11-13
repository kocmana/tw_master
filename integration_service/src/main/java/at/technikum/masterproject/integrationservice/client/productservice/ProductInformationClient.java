package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductInput;
import java.util.List;
import reactor.core.publisher.Mono;

public interface ProductInformationClient {

  Mono<Product> getProductById(int productId);

  Mono<List<Product>> getAllProducts();

  Mono<Integer> saveProduct(CreateProductInput product);

  void updateProduct(UpdateProductInput product);

  void deleteProduct(int id);

}
