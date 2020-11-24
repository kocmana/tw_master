package at.technikum.masterproject.integrationservice.model.customer;

import java.util.List;
import lombok.Value;

@Value
public class CustomerNetwork {

  Integer customerId;
  InteractionType interactionType;
  List<Customer> targetCustomer;
}
