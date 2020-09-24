package at.technikum.masterproject.productservice.productinformation.model.mapper;

import at.technikum.masterproject.productservice.productinformation.model.ProductDimension;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductDimensionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDimensionMapper {

  ProductDimensionDto productDimensionToProductDimensionDto(ProductDimension productDimension);

  ProductDimension productDimensionDtoToProductDimension(ProductDimensionDto productDimensionDto);
}
