package at.technikum.masterproject.customerservice.customernetwork;

import at.technikum.masterproject.customerservice.customernetwork.model.domain.CustomerInteraction;
import at.technikum.masterproject.customerservice.customernetwork.model.domain.CustomerNetwork;
import at.technikum.masterproject.customerservice.customernetwork.model.dto.CustomerInteractionDto;
import at.technikum.masterproject.customerservice.customernetwork.model.dto.CustomerNetworkDto;
import at.technikum.masterproject.customerservice.customernetwork.model.mapper.CustomerRelationshipMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/network")
public class CustomerNetworkController {

  private final CustomerNetworkService customerNetworkService;
  private final CustomerRelationshipMapper customerRelationshipMapper;

  @Autowired
  public CustomerNetworkController(
      CustomerNetworkService customerNetworkService,
      CustomerRelationshipMapper customerRelationshipMapper) {
    this.customerNetworkService = customerNetworkService;
    this.customerRelationshipMapper = customerRelationshipMapper;
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<List<CustomerNetworkDto>> getCustomerNetworkForCustomer(@PathVariable int customerId) {
    List<CustomerNetwork> customerNetworks = customerNetworkService.getCustomerNetworksForCustomer(customerId);
    List<CustomerNetworkDto> customerNetworkDtos = customerNetworks.stream()
        .map(customerRelationshipMapper::toCustomerNetworkDto)
        .collect(Collectors.toUnmodifiableList());
    return ResponseEntity.ok(customerNetworkDtos);
  }

  @PostMapping
  public ResponseEntity<CustomerInteractionDto> postNewCustomerRelationship(
      @RequestBody @Valid CustomerInteractionDto relationshipDto) {
    CustomerInteraction relationship = customerRelationshipMapper
        .toCustomerInteraction(relationshipDto);
    relationship = customerNetworkService.saveCustomerRelationship(relationship);
    relationshipDto = customerRelationshipMapper
        .toCustomerInteractionDto(relationship);
    return ResponseEntity.ok(relationshipDto);
  }

}
