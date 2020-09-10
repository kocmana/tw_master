package at.technikum.masterproject.customernetwork.model.dto;

import at.technikum.masterproject.customernetwork.model.RelationshipType;
import lombok.Data;

@Data
public class CustomerRelationshipDto {

  private Integer sourceCustomerId;
  private RelationshipType relationshipType;
  private Integer targetCustomerId;
}
