package at.technikum.masterproject.price;

import at.technikum.masterproject.price.model.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("price")
public class PriceController {

  private final PriceService priceService;
  private final PriceMapper priceMapper;

  @Autowired
  public PriceController(PriceService priceService, PriceMapper priceMapper) {
    this.priceService = priceService;
    this.priceMapper = priceMapper;
  }
}
