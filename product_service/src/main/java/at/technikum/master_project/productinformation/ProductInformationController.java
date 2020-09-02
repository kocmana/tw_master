package at.technikum.master_project.productinformation;

import at.technikum.master_project.productinformation.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductInformationController {

  private final ProductInformationService productInformationService;

  @Autowired
  public ProductInformationController(ProductInformationService productInformationService) {
    this.productInformationService = productInformationService;
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return productInformationService.retrieveAllProducts();
  }

  @GetMapping(value = "/{id}")
  public Product getProductById(@PathVariable int id) {
    return productInformationService.retrieveProductById(id);
  }
}
