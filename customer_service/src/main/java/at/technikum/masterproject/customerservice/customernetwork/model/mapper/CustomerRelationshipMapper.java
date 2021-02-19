package at.technikum.masterproject.customerservice.customernetwork.model.mapper;

import at.technikum.masterproject.customerservice.customernetwork.model.domain.CustomerInteraction;
import at.technikum.masterproject.customerservice.customernetwork.model.domain.CustomerNetwork;
import at.technikum.masterproject.customerservice.customernetwork.model.dto.CustomerInteractionDto;
import at.technikum.masterproject.customerservice.customernetwork.model.dto.CustomerNetworkDto;
import at.technikum.masterproject.customerservice.customernetwork.model.entity.CustomerInteractionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRelationshipMapper {

  //DTO to Domain Objects
  CustomerNetworkDto toCustomerNetworkDto(CustomerNetwork customerNetwork);

  CustomerInteractionDto toCustomerInteractionDto(CustomerInteraction customerInteraction);

  CustomerInteraction toCustomerInteraction(CustomerInteractionDto customerInteractionDto);

  //Domain Objects to Entities
  @Mapping(source = "sourceCustomerId", target = "id.sourceCustomerId")
  @Mapping(source = "targetCustomerId", target = "id.targetCustomerId")
  @Mapping(source = "interactionType", target = "id.interactionType")
  CustomerInteractionEntity toCustomerInteractionEntity(CustomerInteraction customerInteraction);

  @Mapping(source = "id.sourceCustomerId", target = "sourceCustomerId")
  @Mapping(source = "id.targetCustomerId", target = "targetCustomerId")
  @Mapping(source = "id.interactionType", target = "interactionType")
  CustomerInteraction toCustomerInteraction(CustomerInteractionEntity customerInteractionEntity);

}
