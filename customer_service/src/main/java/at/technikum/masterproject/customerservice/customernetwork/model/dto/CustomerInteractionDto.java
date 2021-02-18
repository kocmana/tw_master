package at.technikum.masterproject.customerservice.customernetwork.model.dto;

import at.technikum.masterproject.customerservice.customernetwork.model.InteractionType;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerInteractionDto {

  @NotNull
  Integer sourceCustomerId;
  @NotNull
  InteractionType interactionType;
  @NotNull
  Integer targetCustomerId;
}
