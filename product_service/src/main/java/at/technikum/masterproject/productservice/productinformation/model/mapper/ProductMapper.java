package at.technikum.masterproject.productservice.productinformation.model.mapper;

import at.technikum.masterproject.productservice.productinformation.model.Product;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductDto productToProductDto(Product product);

  Product productDtoToProduct(ProductDto productDto);
}
