package at.technikum.masterproject.customernetwork.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customer_relationship")
@Data
public class CustomerRelationship {

  @EmbeddedId
  private CustomerRelationshipId id;

  @Embeddable
  @Data
  public static class CustomerRelationshipId implements Serializable {

    @Column(name = "source_customer")
    private Integer sourceCustomerId;
    @Column(name = "relationship_type")
    @Enumerated(EnumType.STRING)
    private RelationshipType relationshipType;
    @Column(name = "target_customer")
    private Integer targetCustomerId;
  }

}
