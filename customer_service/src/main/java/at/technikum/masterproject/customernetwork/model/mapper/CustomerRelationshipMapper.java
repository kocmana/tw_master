package at.technikum.masterproject.customernetwork.model.mapper;

import at.technikum.masterproject.customernetwork.model.CustomerRelationship;
import at.technikum.masterproject.customernetwork.model.dto.CustomerRelationshipDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRelationshipMapper {

  @Mapping(source = "id.sourceCustomerId", target = "sourceCustomerId")
  @Mapping(source = "id.targetCustomerId", target = "targetCustomerId")
  @Mapping(source = "id.relationshipType", target = "relationshipType")
  CustomerRelationshipDto customerRelationshipToCustomerRelationshipDto(CustomerRelationship customerRelationship);

  @Mapping(source = "sourceCustomerId", target = "id.sourceCustomerId")
  @Mapping(source = "targetCustomerId", target = "id.targetCustomerId")
  @Mapping(source = "relationshipType", target = "id.relationshipType")
  CustomerRelationship customerRelationshipDtoToCustomerRelationship(CustomerRelationshipDto customerRelationshipDto);

}
