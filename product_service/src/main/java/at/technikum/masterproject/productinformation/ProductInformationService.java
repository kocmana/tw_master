package at.technikum.masterproject.productinformation;

import at.technikum.masterproject.productinformation.model.Product;
import at.technikum.masterproject.productinformation.model.ProductInformationNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductInformationService {

  private final ProductInformationRepository productInformationRepository;

  @Autowired
  public ProductInformationService(
      ProductInformationRepository productInformationRepository) {
    this.productInformationRepository = productInformationRepository;
  }

  List<Product> retrieveAllProducts() {
    return productInformationRepository.findAll();
  }

  List<Product> retrieveAllProductsWithPagination(Pageable pageable) {
    return productInformationRepository.findAll(pageable).toList();
  }

  public Product retrieveProductById(int productId) {
    return productInformationRepository.findById(productId)
        .orElseThrow(() -> generateNotFoundException(productId));
  }

  Integer saveNewProduct(Product product) {
    Product savedProduct = productInformationRepository.save(product);
    return savedProduct.getId();
  }

  void updateProduct(Product product) {
    retrieveProductById(product.getId());
    productInformationRepository.save(product);
  }

  private ProductInformationNotFoundException generateNotFoundException(int productId) {
    String message = String.format("No product with ID %d found.", productId);
    return new ProductInformationNotFoundException(message);
  }

}
