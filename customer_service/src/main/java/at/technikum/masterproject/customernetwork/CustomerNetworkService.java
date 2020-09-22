package at.technikum.masterproject.customernetwork;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toConcurrentMap;
import static java.util.stream.Collectors.toUnmodifiableList;

import at.technikum.masterproject.customerinformation.CustomerInformationService;
import at.technikum.masterproject.customerinformation.model.Customer;
import at.technikum.masterproject.customernetwork.model.CustomerInteraction;
import at.technikum.masterproject.customernetwork.model.CustomerInteraction.CustomerRelationshipId;
import at.technikum.masterproject.customernetwork.model.CustomerNetwork;
import at.technikum.masterproject.customernetwork.model.InteractionType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CustomerNetworkService {

  private final CustomerNetworkRepository customerNetworkRepository;
  private final CustomerInformationService customerInformationService;

  @Autowired
  public CustomerNetworkService(
      CustomerNetworkRepository customerNetworkRepository,
      CustomerInformationService customerInformationService) {
    this.customerNetworkRepository = customerNetworkRepository;
    this.customerInformationService = customerInformationService;
  }

  CustomerInteraction saveCustomerRelationship(CustomerInteraction customerInteraction) {
    return customerNetworkRepository.save(customerInteraction);
  }

  List<CustomerNetwork> getCustomerNetworksForCustomer(Integer customerId) {
    List<CustomerInteraction> network = customerNetworkRepository
        .findByIdSourceCustomerId(customerId);
    List<Integer> targetCustomerIds = extractTargetCustomerIds(network);
    Map<Integer, Customer> targetCustomers = retrieveCustomerInformationForTargetCustomers(targetCustomerIds);
    Map<InteractionType, List<CustomerRelationshipId>> customersPerRelationshipType =
        groupCustomersPerRelationshipType(network);

    return transformToCustomerNetworks(customersPerRelationshipType, targetCustomers);
  }

  private List<Integer> extractTargetCustomerIds(List<CustomerInteraction> network) {
    return network.stream()
        .map(CustomerInteraction::getId)
        .map(CustomerRelationshipId::getTargetCustomerId)
        .collect(toUnmodifiableList());
  }

  private Map<Integer, Customer> retrieveCustomerInformationForTargetCustomers(List<Integer> targetCustomerIds) {
    return customerInformationService.retrieveCustomersByIds(targetCustomerIds).stream()
        .collect(toConcurrentMap(Customer::getCustomerId, Function.identity()));
  }

  private Map<InteractionType, List<CustomerRelationshipId>> groupCustomersPerRelationshipType(
      List<CustomerInteraction> network) {
    return network
        .stream().map(CustomerInteraction::getId)
        .collect(groupingBy(CustomerRelationshipId::getInteractionType));
  }

  private List<CustomerNetwork> transformToCustomerNetworks(
      Map<InteractionType, List<CustomerRelationshipId>> customersPerRelationshipType,
      Map<Integer, Customer> targetCustomers) {
    return customersPerRelationshipType.entrySet().stream()
        .map(entry -> new CustomerNetwork(entry.getKey(), entry.getValue().stream()
            .map(CustomerRelationshipId::getTargetCustomerId)
            .map(targetCustomers::get)
            .collect(toUnmodifiableList())))
        .collect(toUnmodifiableList());
  }
}
