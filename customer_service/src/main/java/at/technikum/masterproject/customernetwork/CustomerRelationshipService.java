package at.technikum.masterproject.customernetwork;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toConcurrentMap;
import static java.util.stream.Collectors.toUnmodifiableList;

import at.technikum.masterproject.customerinformation.CustomerInformationService;
import at.technikum.masterproject.customerinformation.model.Customer;
import at.technikum.masterproject.customernetwork.model.CustomerNetwork;
import at.technikum.masterproject.customernetwork.model.CustomerRelationship;
import at.technikum.masterproject.customernetwork.model.CustomerRelationship.CustomerRelationshipId;
import at.technikum.masterproject.customernetwork.model.RelationshipType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CustomerRelationshipService {

  private final CustomerRelationshipRepository customerRelationshipRepository;
  private final CustomerInformationService customerInformationService;

  @Autowired
  public CustomerRelationshipService(
      CustomerRelationshipRepository customerRelationshipRepository,
      CustomerInformationService customerInformationService) {
    this.customerRelationshipRepository = customerRelationshipRepository;
    this.customerInformationService = customerInformationService;
  }

  CustomerRelationship saveCustomerRelationship(CustomerRelationship customerRelationship) {
    return customerRelationshipRepository.save(customerRelationship);
  }

  List<CustomerNetwork> getCustomerNetworksForCustomer(Integer customerId) {
    List<CustomerRelationship> network = customerRelationshipRepository
        .findByIdSourceCustomerId(customerId);
    List<Integer> targetCustomerIds = extractTargetCustomerIds(network);
    Map<Integer, Customer> targetCustomers = retrieveCustomerInformationForTargetCustomers(targetCustomerIds);
    Map<RelationshipType, List<CustomerRelationshipId>> customersPerRelationshipType =
        groupCustomersPerRelationshipType(network);

    return transformToCustomerNetworks(customersPerRelationshipType, targetCustomers);
  }

  private List<Integer> extractTargetCustomerIds(List<CustomerRelationship> network) {
    return network.stream()
        .map(CustomerRelationship::getId)
        .map(CustomerRelationshipId::getTargetCustomerId)
        .collect(toUnmodifiableList());
  }

  private Map<Integer, Customer> retrieveCustomerInformationForTargetCustomers(List<Integer> targetCustomerIds) {
    return customerInformationService.retrieveCustomersByIds(targetCustomerIds).stream()
        .collect(toConcurrentMap(Customer::getCustomerId, Function.identity()));
  }

  private Map<RelationshipType, List<CustomerRelationshipId>> groupCustomersPerRelationshipType(
      List<CustomerRelationship> network) {
    return network
        .stream().map(CustomerRelationship::getId)
        .collect(groupingBy(CustomerRelationshipId::getRelationshipType));
  }

  private List<CustomerNetwork> transformToCustomerNetworks(
      Map<RelationshipType, List<CustomerRelationshipId>> customersPerRelationshipType,
      Map<Integer, Customer> targetCustomers) {
    return customersPerRelationshipType.entrySet().stream()
        .map(entry -> new CustomerNetwork(entry.getKey(), entry.getValue().stream()
            .map(CustomerRelationshipId::getTargetCustomerId)
            .map(targetCustomers::get)
            .collect(toUnmodifiableList())))
        .collect(toUnmodifiableList());
  }
}
