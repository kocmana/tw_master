package at.technikum.masterproject.customerservice.customernetwork.model.dto;

import at.technikum.masterproject.customerservice.customernetwork.model.InteractionType;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerInteractionDto {

  @NotNull
  private Integer sourceCustomerId;
  @NotNull
  private InteractionType interactionType;
  @NotNull
  private Integer targetCustomerId;
}
