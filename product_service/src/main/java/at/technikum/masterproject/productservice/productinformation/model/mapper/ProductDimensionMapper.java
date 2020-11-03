package at.technikum.masterproject.productservice.productinformation.model.mapper;

import at.technikum.masterproject.productservice.productinformation.model.ProductDimension;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductDimensionDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDimensionMapper {

  ProductDimension productDimensionDtoToProductDimension(ProductDimensionDto productDimensionDto);

  @InheritInverseConfiguration
  ProductDimensionDto productDimensionToProductDimensionDto(ProductDimension productDimension);

}


