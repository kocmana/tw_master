package at.technikum.masterproject.customernetwork.model.dto;

import at.technikum.masterproject.customernetwork.model.RelationshipType;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRelationshipDto {

  @NotNull
  private Integer sourceCustomerId;
  @NotNull
  private RelationshipType relationshipType;
  @NotNull
  private Integer targetCustomerId;
}
