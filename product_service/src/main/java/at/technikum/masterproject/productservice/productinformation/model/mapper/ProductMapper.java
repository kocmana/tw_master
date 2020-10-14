package at.technikum.masterproject.productservice.productinformation.model.mapper;

import at.technikum.masterproject.productservice.productinformation.model.Product;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductCreationRequest;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductResponse;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductUpdateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductResponse productToProductResponse(Product product);

  Product productCreationRequestToProduct(ProductCreationRequest productCreationRequest);

  Product productUpdateRequestToProduct(ProductUpdateRequest productUpdateRequest);
}
