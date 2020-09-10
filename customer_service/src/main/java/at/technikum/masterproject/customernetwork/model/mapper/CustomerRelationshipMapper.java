package at.technikum.masterproject.customernetwork.model.mapper;

import at.technikum.masterproject.customernetwork.model.CustomerRelationship;
import at.technikum.masterproject.customernetwork.model.dto.CustomerRelationshipDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerRelationshipMapper {

  CustomerRelationshipDto customerRelationshipToCustomerRelationshipDto(CustomerRelationship customerRelationship);

  CustomerRelationship customerRelationshipDtoToCustomerRelationship(CustomerRelationshipDto customerRelationshipDto);

}
