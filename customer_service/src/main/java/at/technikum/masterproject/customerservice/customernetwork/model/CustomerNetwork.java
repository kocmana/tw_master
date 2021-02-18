package at.technikum.masterproject.customerservice.customernetwork.model;

import at.technikum.masterproject.customerservice.customerinformation.model.domain.Customer;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CustomerNetwork {

  private InteractionType interactionType;
  private List<Customer> targetCustomer;
}
