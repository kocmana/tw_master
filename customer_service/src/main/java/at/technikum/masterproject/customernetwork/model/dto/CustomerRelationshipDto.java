package at.technikum.masterproject.customernetwork.model.dto;

import at.technikum.masterproject.customernetwork.model.RelationshipType;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerRelationshipDto {

  @NotBlank
  private Integer sourceCustomerId;
  @NotBlank
  private RelationshipType relationshipType;
  @NotBlank
  private Integer targetCustomerId;
}
