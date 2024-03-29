package at.technikum.masterproject.ecommerceservice.price;

import at.technikum.masterproject.ecommerceservice.price.model.PriceNotFoundException;
import at.technikum.masterproject.ecommerceservice.price.model.domain.Price;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

  private final PriceRepository priceRepository;

  @Autowired
  PriceService(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  public Price getCurrentPriceForProduct(Integer productId) {
    return priceRepository.findPriceByProductIdThatIsValidAtDateTime(productId, LocalDateTime.now())
        .orElseThrow(() -> generatePriceNotFoundException(productId));
  }

  private PriceNotFoundException generatePriceNotFoundException(Integer productId) {
    return new PriceNotFoundException(productId);
  }

  List<Price> getPricesForProductAndTimeframe(Integer productId, LocalDateTime from, LocalDateTime to) {
    checkIfDateArgumentsAreValid(from, to);
    return priceRepository.getPricesByProductIdValidInTimeframe(productId, from, to);
  }

  private void checkIfDateArgumentsAreValid(LocalDateTime from, LocalDateTime to) {
    if (from.isAfter(to) || from.isEqual(to)) {
      throw new IllegalArgumentException(String.format("Invalid Arguments: %tF is not before %tF", from, to));
    }
  }

  Price savePriceForProduct(Price price) {
    checkIfDateArgumentsAreValid(price.getValidFrom(), price.getValidTo());
    checkIfParallelPricingExists(price);

    return priceRepository.save(price);
  }

  private void checkIfParallelPricingExists(Price price) {
    boolean parallelPricingExists = !getPricesForProductAndTimeframe(price.getProductId(), price.getValidFrom(),
        price.getValidTo()).isEmpty();
    if (parallelPricingExists) {
      throw new IllegalArgumentException(
          String.format("Conflicting timeframes exist for product with ID %d between %tF and %tF",
              price.getProductId(), price.getValidFrom(), price.getValidTo()));
    }
  }

  List<Price> getAllPricesForProduct(Integer productId) {
    return priceRepository.findAllByProductIdOrderByValidFrom(productId);
  }
}
