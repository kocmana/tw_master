package at.technikum.masterproject.integrationservice.model.customer.dto;

import at.technikum.masterproject.integrationservice.model.customer.InteractionType;
import lombok.Value;

@Value
public class CreateCustomerInteractionInput {

  Integer sourceCustomerId;
  InteractionType interactionType;
  Integer targetCustomerId;
}
