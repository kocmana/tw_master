package at.technikum.master_project.productinformation;

import at.technikum.master_project.productinformation.model.Product;
import at.technikum.master_project.productinformation.model.ProductInformationNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class ProductInformationService {

  ProductInformationRepository productInformationRepository;

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

  Product retrieveProductById(int productId){
    return productInformationRepository.findById(productId)
        .orElseThrow(() -> generateNotFoundException(productId));
  }

  private ProductInformationNotFoundException generateNotFoundException(int productId) {
    String message = String.format("No product with ID %d found.", productId);
    return new ProductInformationNotFoundException(message);
  }

}
