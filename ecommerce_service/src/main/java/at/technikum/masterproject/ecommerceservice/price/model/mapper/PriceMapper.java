package at.technikum.masterproject.ecommerceservice.price.model.mapper;

import at.technikum.masterproject.ecommerceservice.price.model.Price;
import at.technikum.masterproject.ecommerceservice.price.model.dto.PriceDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  PriceDto priceToPriceDto(Price price);

  @InheritInverseConfiguration
  Price priceDtoToPrice(PriceDto priceDto);

}
