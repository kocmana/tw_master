package at.technikum.masterproject.ecommerceservice.price;

import static java.util.stream.Collectors.toUnmodifiableList;

import at.technikum.masterproject.ecommerceservice.price.model.Price;
import at.technikum.masterproject.ecommerceservice.price.model.dto.PriceDto;
import at.technikum.masterproject.ecommerceservice.price.model.mapper.PriceMapper;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public class PriceController {

  private final PriceService priceService;
  private final PriceMapper priceMapper;

  @Autowired
  public PriceController(PriceService priceService, PriceMapper priceMapper) {
    this.priceService = priceService;
    this.priceMapper = priceMapper;
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<PriceDto> getCurrentPriceForProduct(
      @PathVariable @Valid @NotNull Integer productId) {
    Price price = priceService.getCurrentPriceForProduct(productId);
    PriceDto priceDto = priceMapper.priceToPriceDto(price);
    return ResponseEntity.ok(priceDto);
  }

  @GetMapping("/product/{productId}/all")
  public ResponseEntity<List<PriceDto>> getAllPricesForProduct(
      @PathVariable @Valid @NotNull Integer productId) {
    List<Price> prices = priceService.getAllPricesForProduct(productId);
    return ResponseEntity.ok(toDtos(prices));
  }

  @GetMapping(value = "/product/{productId}", params = {"from", "to"})
  public ResponseEntity<List<PriceDto>> getPricesForProductAndTimeframe(
      @PathVariable @Valid @NotNull Integer productId,
      @RequestParam @Valid @NotNull LocalDateTime from,
      @RequestParam @Valid @NotNull LocalDateTime to) {
    List<Price> prices = priceService.getPricesForProductAndTimeframe(productId, from, to);

    return ResponseEntity.ok(toDtos(prices));
  }

  @PostMapping
  public ResponseEntity<PriceDto> savePrice(@RequestBody @Valid PriceDto priceDto) {
    Price price = priceMapper.priceDtoToPrice(priceDto);
    Price savedPrice = priceService.savePriceForProduct(price);
    PriceDto response = priceMapper.priceToPriceDto(savedPrice);
    return ResponseEntity.ok().body(response);
  }

  private List<PriceDto> toDtos(List<Price> prices) {
    return prices.stream()
        .map(priceMapper::priceToPriceDto)
        .collect(toUnmodifiableList());
  }

}
