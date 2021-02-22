package at.technikum.masterproject.customerservice.customernetwork.model.domain;

import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomerInteractionId implements Serializable {

  private Integer sourceCustomerId;
  @Enumerated(EnumType.STRING)
  private InteractionType interactionType;
  private Integer targetCustomerId;
}
