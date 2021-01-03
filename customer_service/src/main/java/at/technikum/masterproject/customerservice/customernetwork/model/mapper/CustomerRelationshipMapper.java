package at.technikum.masterproject.customerservice.customernetwork.model.mapper;

import at.technikum.masterproject.customerservice.customernetwork.model.CustomerInteraction;
import at.technikum.masterproject.customerservice.customernetwork.model.CustomerNetwork;
import at.technikum.masterproject.customerservice.customernetwork.model.dto.CustomerInteractionDto;
import at.technikum.masterproject.customerservice.customernetwork.model.dto.CustomerNetworkResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRelationshipMapper {

  CustomerNetworkResponse customerNetworkToCustomerNetworkResponse (CustomerNetwork customerNetwork);

  @Mapping(source = "id.sourceCustomerId", target = "sourceCustomerId")
  @Mapping(source = "id.targetCustomerId", target = "targetCustomerId")
  @Mapping(source = "id.interactionType", target = "interactionType")
  CustomerInteractionDto customerInteractionToCustomerInteractionDto(
      CustomerInteraction customerInteraction);

  @Mapping(source = "sourceCustomerId", target = "id.sourceCustomerId")
  @Mapping(source = "targetCustomerId", target = "id.targetCustomerId")
  @Mapping(source = "interactionType", target = "id.interactionType")
  CustomerInteraction customerInteractionDtoToCustomerInteraction(
      CustomerInteractionDto customerInteractionDto);

}
