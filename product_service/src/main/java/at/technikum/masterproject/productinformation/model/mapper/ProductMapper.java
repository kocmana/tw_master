package at.technikum.masterproject.productinformation.model.mapper;

import at.technikum.masterproject.productinformation.model.Product;
import at.technikum.masterproject.productinformation.model.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductDto productToProductDto(Product product);

  Product productDtoToProduct(ProductDto productDto);
}
