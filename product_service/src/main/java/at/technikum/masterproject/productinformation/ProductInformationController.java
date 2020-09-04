package at.technikum.masterproject.productinformation;

import at.technikum.masterproject.delay.annotation.NormallyDistributedEndpointDelay;
import at.technikum.masterproject.delay.annotation.ProbabilisticEndpointDelay;
import at.technikum.masterproject.productinformation.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
  @NormallyDistributedEndpointDelay(mean = 1000, standardDeviation = 500)
  public List<Product> getAllProducts(Pageable pageable) {
    return productInformationService.retrieveAllProductsWithPagination(pageable);
  }

  @GetMapping(value = "/{id}")
  @ProbabilisticEndpointDelay(probability = 0.5f, duration = 3000)
  public Product getProductById(@PathVariable int id) {
    return productInformationService.retrieveProductById(id);
  }
}
