package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductInformationClient {

  Mono<Product> getProductById(int id);

  Flux<Product> getAllProducts();

  Mono<Product> saveProduct(Product product);

  Mono<Product> updateProduct(Product product);
}
