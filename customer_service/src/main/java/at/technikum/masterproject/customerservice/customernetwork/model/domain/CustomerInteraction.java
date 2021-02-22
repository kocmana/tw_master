package at.technikum.masterproject.customerservice.customernetwork.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_interaction")
@IdClass(CustomerInteractionId.class)
@Data
@NoArgsConstructor
public class CustomerInteraction {

  @Id
  @Column(name = "source_customer_id")
  private Integer sourceCustomerId;
  @Id
  @Column(name = "interaction_type")
  @Enumerated(EnumType.STRING)
  private InteractionType interactionType;
  @Id
  @Column(name = "target_customer_id")
  private Integer targetCustomerId;

}
