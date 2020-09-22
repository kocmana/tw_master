package at.technikum.masterproject.ecommerce.price.model.mapper;

import at.technikum.masterproject.ecommerce.price.model.Price;
import at.technikum.masterproject.ecommerce.price.model.dto.PriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  PriceDto priceToPriceDto(Price price);

  Price priceDtoToPrice(PriceDto priceDto);

}
