package at.technikum.masterproject.ecommerceservice.price.model;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE_PRODUCT_ID = "No price found for product with product Id %d";
  private static final String MESSAGE_TEMPLATE_PRODUCT_ID_AND_TIMEFRAME =
      "No price found for product with product Id %d between %tF and %tF";

  public PriceNotFoundException(Integer productId) {
    super(String.format(MESSAGE_TEMPLATE_PRODUCT_ID, productId));
  }

  public PriceNotFoundException(Integer productId, LocalDateTime from, LocalDateTime to) {
    super(String.format(MESSAGE_TEMPLATE_PRODUCT_ID_AND_TIMEFRAME, productId, from, to));
  }

}
