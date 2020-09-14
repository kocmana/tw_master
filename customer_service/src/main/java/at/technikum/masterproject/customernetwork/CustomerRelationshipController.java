package at.technikum.masterproject.customernetwork;

import at.technikum.masterproject.customernetwork.model.CustomerNetwork;
import at.technikum.masterproject.customernetwork.model.CustomerRelationship;
import at.technikum.masterproject.customernetwork.model.dto.CustomerRelationshipDto;
import at.technikum.masterproject.customernetwork.model.mapper.CustomerRelationshipMapper;
import java.util.List;
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
@RequestMapping("/relationships")
public class CustomerRelationshipController {

  private final CustomerRelationshipService customerRelationshipService;
  private final CustomerRelationshipMapper customerRelationshipMapper;

  @Autowired
  public CustomerRelationshipController(
      CustomerRelationshipService customerRelationshipService,
      CustomerRelationshipMapper customerRelationshipMapper) {
    this.customerRelationshipService = customerRelationshipService;
    this.customerRelationshipMapper = customerRelationshipMapper;
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<List<CustomerNetwork>> getCustomerNetworkForCustomer(@PathVariable Integer customerId) {
    List<CustomerNetwork> customerNetworks = customerRelationshipService.getCustomerNetworksForCustomer(customerId);
    return ResponseEntity.ok(customerNetworks);
  }

  @PostMapping
  public ResponseEntity<CustomerRelationshipDto> postNewCustomerRelationship(
      @RequestBody @Valid CustomerRelationshipDto relationshipDto) {
    CustomerRelationship relationship = customerRelationshipMapper
        .customerRelationshipDtoToCustomerRelationship(relationshipDto);
    relationship = customerRelationshipService.saveCustomerRelationship(relationship);
    relationshipDto = customerRelationshipMapper
        .customerRelationshipToCustomerRelationshipDto(relationship);
    return ResponseEntity.ok(relationshipDto);
  }

}
