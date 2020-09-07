package at.technikum.masterproject.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProductInformationRepository {

  private final RestTemplate restTemplate;

  public ProductInformationRepository(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }



}
