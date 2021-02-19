package at.technikum.masterproject.customerservice.customernetwork.model.dto;

import at.technikum.masterproject.customerservice.customerinformation.model.domain.Customer;
import at.technikum.masterproject.customerservice.customernetwork.model.domain.InteractionType;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerNetworkDto {

  InteractionType interactionType;
  List<Customer> targetCustomer;
}
