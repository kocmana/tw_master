package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.EcommerceServiceException;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePurchaseInput;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PurchaseClientImpl implements PurchaseClient {

  private static final String PURCHASE_ENDPOINT = "/purchase";
  private static final String PURCHASE_BY_PURCHASE_ID_ENDPOINT = "/purchase/{purchaseId}";
  private static final String PURCHASE_BY_CUSTOMER_ENDPOINT = "/purchase/customer/{customerId}";

  private final RestTemplate restTemplate;

  @Autowired
  public PurchaseClientImpl(@Qualifier("ecommerceServiceRestTemplate") RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Purchase getPurchase(int purchaseId) {
    return restTemplate.getForObject(
        PURCHASE_BY_PURCHASE_ID_ENDPOINT,
        Purchase.class,
        purchaseId
    );
  }

  @Override
  public List<Purchase> getPurchasesForCustomer(int customerId) {
    ResponseEntity<List<Purchase>> response = restTemplate.exchange(
        PURCHASE_BY_CUSTOMER_ENDPOINT,
        HttpMethod.GET,
        HttpEntity.EMPTY,
        new ParameterizedTypeReference<List<Purchase>>() {
        },
        customerId
    );

    return Optional.ofNullable(response.getBody())
        .orElse(Collections.emptyList());
  }

  @Override
  public long savePurchase(CreatePurchaseInput purchase) {
    Purchase response = restTemplate.postForObject(
        PURCHASE_ENDPOINT,
        purchase,
        Purchase.class
    );
    return Optional.ofNullable(response)
        .orElseThrow(() -> new EcommerceServiceException("Could not retrieve element id from response."))
        .getId();
  }

}
