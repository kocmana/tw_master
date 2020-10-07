package at.technikum.masterproject.integrationservice.model.customer;

import lombok.Value;

@Value
public class CustomerInteraction {

  Integer sourceCustomerId;
  InteractionType interactionType;
  Integer targetCustomerId;
}
