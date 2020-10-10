package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.Product;
import java.util.List;

public interface ProductInformationClient {

  Product getProductById(int id);

  List<Product> getAllProducts();

  Product saveProduct(Product product);

  Product updateProduct(Product product);
}
