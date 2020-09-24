package at.technikum.masterproject.productservice.productinformation;

import at.technikum.masterproject.commons.delay.annotation.FixedEndpointDelay;
import at.technikum.masterproject.commons.delay.annotation.NormallyDistributedEndpointDelay;
import at.technikum.masterproject.commons.delay.annotation.ProbabilisticEndpointDelay;
import at.technikum.masterproject.productservice.model.ElementCreationResponse;
import at.technikum.masterproject.productservice.productinformation.model.Product;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductDto;
import at.technikum.masterproject.productservice.productinformation.model.mapper.ProductMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  @NormallyDistributedEndpointDelay(mean = 1000, standardDeviation = 500)
  public ResponseEntity<List<ProductDto>> getAllProducts(Pageable pageable) {
    List<Product> products = productInformationService.retrieveAllProductsWithPagination(pageable);
    List<ProductDto> productDtos = products.stream()
        .map(productMapper::productToProductDto)
        .collect(Collectors.toUnmodifiableList());
    return ResponseEntity.ok(productDtos);
  }

  @GetMapping(value = "/{id}")
  @ProbabilisticEndpointDelay(probability = 0.5f, duration = 3000)
  public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
    Product product = productInformationService.retrieveProductById(id);
    return ResponseEntity.ok(productMapper.productToProductDto(product));
  }

  @PostMapping
  @FixedEndpointDelay(delayInMs = 100)
  public ResponseEntity<ElementCreationResponse> saveProduct(@RequestBody @Valid ProductDto productDto) {
    Product productToSave = productMapper.productDtoToProduct(productDto);
    int idOfNewProduct = productInformationService.saveNewProduct(productToSave);
    return ResponseEntity.ok(new ElementCreationResponse(idOfNewProduct));
  }

  @PatchMapping
  @FixedEndpointDelay(delayInMs = 100)
  public void updateProductInformation(@RequestBody @Valid ProductDto productDto) {
    productInformationService.updateProduct(productMapper.productDtoToProduct(productDto));
  }
}
