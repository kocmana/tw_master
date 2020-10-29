package at.technikum.masterproject.productservice.productinformation;

import static org.springframework.http.ResponseEntity.ok;

import at.technikum.masterproject.productservice.model.ElementCreationResponse;
import at.technikum.masterproject.productservice.productinformation.model.Product;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductCreationRequest;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductResponse;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductUpdateRequest;
import at.technikum.masterproject.productservice.productinformation.model.mapper.ProductMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductInformationController {

  private final ProductMapper productMapper;
  private final ProductInformationService productInformationService;

  @Autowired
  public ProductInformationController(
      ProductMapper productMapper,
      ProductInformationService productInformationService) {
    this.productMapper = productMapper;
    this.productInformationService = productInformationService;
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAllProducts(Pageable pageable) {
    List<Product> products = productInformationService.retrieveAllProductsWithPagination(pageable);
    List<ProductResponse> productResponses = products.stream()
        .map(productMapper::productToProductResponse)
        .collect(Collectors.toUnmodifiableList());
    return ok(productResponses);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
    Product product = productInformationService.retrieveProductById(id);
    return ok(productMapper.productToProductResponse(product));
  }

  @DeleteMapping(value = "/{id}")
  public void deleteProductById(@PathVariable @NotNull Integer id) {
    productInformationService.deleteProductById(id);
  }

  @PostMapping
  public ResponseEntity<ElementCreationResponse> saveProduct(@RequestBody @Valid ProductCreationRequest product) {
    Product productToSave = productMapper.productCreationRequestToProduct(product);
    Integer idOfNewProduct = productInformationService.saveNewProduct(productToSave);
    return ok(new ElementCreationResponse(idOfNewProduct));
  }

  @PutMapping
  public void updateProductInformation(@RequestBody @Valid ProductUpdateRequest product) {
    Product productToUpdate = productMapper.productUpdateRequestToProduct(product);
    productInformationService.updateProduct(productToUpdate);
  }
}
