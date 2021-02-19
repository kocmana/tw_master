package at.technikum.masterproject.customerservice.customernetwork.model.domain;

import at.technikum.masterproject.customerservice.customerinformation.model.domain.Customer;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerNetwork {

  private InteractionType interactionType;
  private List<Customer> targetCustomer;
}
