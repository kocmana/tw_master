package at.technikum.masterproject.customerservice.customernetwork.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_interaction")
@Data
@NoArgsConstructor
public class CustomerInteractionEntity {

  @EmbeddedId
  private CustomerRelationshipId id;

  @Embeddable
  @Data
  public static class CustomerRelationshipId implements Serializable {

    @Column(name = "source_customer")
    private Integer sourceCustomerId;
    @Column(name = "relationship_type")
    private String interactionType;
    @Column(name = "target_customer")
    private Integer targetCustomerId;
  }

}
