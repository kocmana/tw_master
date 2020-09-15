package at.technikum.masterproject.price.model.mapper;

import at.technikum.masterproject.price.model.Price;
import at.technikum.masterproject.price.model.dto.PriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  PriceDto priceToPriceDto(Price price);

  Price priceToPriceDto(PriceDto priceDto);

}
