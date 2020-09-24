package at.technikum.masterproject.customerservice.customernetwork.model;

import at.technikum.masterproject.customerservice.customerinformation.model.Customer;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomerNetwork {

  private InteractionType interactionType;
  private List<Customer> targetCustomer;
}
