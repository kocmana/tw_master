package at.technikum.master_project.productinformation;

import at.technikum.master_project.productinformation.model.Product;
import at.technikum.master_project.productinformation.model.ProductInformationNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

  Product retrieveProductById(int productId){
    return productInformationRepository.findById(productId)
        .orElseThrow(() -> generateNotFoundException(productId));
  }

  private ProductInformationNotFoundException generateNotFoundException(int productId) {
    String message = String.format("No product with ID %d found.", productId);
    return new ProductInformationNotFoundException(message);
  }

}
