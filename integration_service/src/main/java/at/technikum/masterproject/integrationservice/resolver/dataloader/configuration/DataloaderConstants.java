package at.technikum.masterproject.integrationservice.resolver.dataloader.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataloaderConstants {

  public static final String CUSTOMER_INFORMATION_DATALOADER = "CUSTOMER_INFORMATION_DATALOADER";
  public static final String CUSTOMER_NETWORK_DATALOADER = "CUSTOMER_NETWORK_DATALOADER";

  public static final String PRODUCT_INFORMATION_DATALOADER = "PRODUCT_INFORMATION_DATALOADER";
  public static final String PRODUCT_REVIEW_BY_PRODUCT_DATALOADER = "PRODUCT_REVIEW_BY_PRODUCT_DATALOADER";
  public static final String PRODUCT_REVIEW_BY_CUSTOMER_DATALOADER = "PRODUCT_REVIEW_BY_PRODUCT_DATALOADER";

  public static final String PURCHASE_BY_ID_DATALOADER = "PURCHASE_BY_ID_DATALOADER";
  public static final String PURCHASE_BY_CUSTOMER_DATALOADER = "PURCHASE_BY_CUSTOMER_DATALOADER";

  public static final String CURRENT_PRICE_FOR_PRODUCT_DATALOADER = "CURRENT_PRICE_FOR_PRODUCT_DATALOADER";
  public static final String ALL_PRICES_FOR_PRODUCT_DATALOADER = "ALL_PRICES_FOR_PRODUCT_DATALOADER";
}
