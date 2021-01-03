package at.technikum.masterproject.productservice.productinformation;

import at.technikum.masterproject.productservice.productinformation.model.Product;
import at.technikum.masterproject.productservice.productinformation.model.ProductInformationNotFoundException;
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

  public List<Product> retrieveAllProducts() {
    return productInformationRepository.findAll();
  }

  public List<Product> retrieveAllProductsWithPagination(Pageable pageable) {
    return productInformationRepository.findAll(pageable).toList();
  }

  public Product retrieveProductById(Integer productId) {
    return productInformationRepository.findById(productId)
        .orElseThrow(() -> generateNotFoundException(productId));
  }

  public Integer saveNewProduct(Product product) {
    Product savedProduct = productInformationRepository.save(product);
    return savedProduct.getId();
  }

  public void updateProduct(Product product) {
    if (productDoesNotExist(product.getId())) {
      throw generateNotFoundException(product.getId());
    }
    productInformationRepository.save(product);
  }

  public void deleteProductById(Integer id) {
    if (productDoesNotExist(id)) {
      throw generateNotFoundException(id);
    }
    productInformationRepository.deleteById(id);
  }

  public boolean productDoesNotExist(Integer productId) {
    return !productInformationRepository.existsById(productId);
  }

  private ProductInformationNotFoundException generateNotFoundException(int productId) {
    return new ProductInformationNotFoundException(productId);
  }
}
