package at.technikum.masterproject.productinformation.model.mapper;

import at.technikum.masterproject.productinformation.model.ProductDimension;
import at.technikum.masterproject.productinformation.model.dto.ProductDimensionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDimensionMapper {

  ProductDimensionDto productDimensionToProductDimensionDto(ProductDimension productDimension);

  ProductDimension productDimensionDtoToProductDimension(ProductDimensionDto productDimensionDto);
}
