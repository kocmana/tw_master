package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductInput;
import java.util.List;

public interface ProductInformationClient {

  Product getProductById(int id);

  List<Product> getAllProducts();

  Integer saveProduct(CreateProductInput product);

  void updateProduct(UpdateProductInput product);
}
