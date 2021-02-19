package at.technikum.masterproject.customerservice.customernetwork.model.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerInteraction {

  private Integer sourceCustomerId;
  private InteractionType interactionType;
  private Integer targetCustomerId;
}
