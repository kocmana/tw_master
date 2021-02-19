package at.technikum.masterproject.customerservice.customernetwork;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toConcurrentMap;
import static java.util.stream.Collectors.toUnmodifiableList;

import at.technikum.masterproject.customerservice.customerinformation.CustomerInformationService;
import at.technikum.masterproject.customerservice.customerinformation.model.domain.Customer;
import at.technikum.masterproject.customerservice.customernetwork.model.domain.CustomerInteraction;
import at.technikum.masterproject.customerservice.customernetwork.model.domain.CustomerNetwork;
import at.technikum.masterproject.customerservice.customernetwork.model.domain.InteractionType;
import at.technikum.masterproject.customerservice.customernetwork.model.entity.CustomerInteractionEntity;
import at.technikum.masterproject.customerservice.customernetwork.model.mapper.CustomerRelationshipMapper;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CustomerNetworkService {

  private final CustomerNetworkRepository customerNetworkRepository;
  private final CustomerInformationService customerInformationService;
  private final CustomerRelationshipMapper customerRelationshipMapper;

  @Autowired
  public CustomerNetworkService(
      CustomerNetworkRepository customerNetworkRepository,
      CustomerInformationService customerInformationService,
      CustomerRelationshipMapper customerRelationshipMapper) {
    this.customerNetworkRepository = customerNetworkRepository;
    this.customerInformationService = customerInformationService;
    this.customerRelationshipMapper = customerRelationshipMapper;
  }

  CustomerInteraction saveCustomerRelationship(CustomerInteraction customerInteraction) {
    CustomerInteractionEntity customerInteractionEntity = customerRelationshipMapper
        .toCustomerInteractionEntity(customerInteraction);
    customerInteractionEntity = customerNetworkRepository.save(customerInteractionEntity);
    return customerRelationshipMapper.toCustomerInteraction(customerInteractionEntity);
  }

  List<CustomerNetwork> getCustomerNetworksForCustomer(Integer customerId) {
    List<CustomerInteraction> network = customerNetworkRepository
        .findByIdSourceCustomerId(customerId).stream()
        .map(customerRelationshipMapper::toCustomerInteraction)
        .collect(Collectors.toList());

    List<Integer> targetCustomerIds = extractTargetCustomerIds(network);
    Map<Integer, Customer> targetCustomersByCustomerId = retrieveCustomerInformationForTargetCustomers(targetCustomerIds);
    Map<InteractionType, List<CustomerInteraction>> customersByInteractionType =
        groupCustomersPerRelationshipType(network);

    return transformToCustomerNetworks(customersByInteractionType, targetCustomersByCustomerId);
  }

  private List<Integer> extractTargetCustomerIds(List<CustomerInteraction> network) {
    return network.stream()
        .map(CustomerInteraction::getTargetCustomerId)
        .collect(toUnmodifiableList());
  }

  private Map<Integer, Customer> retrieveCustomerInformationForTargetCustomers(List<Integer> targetCustomerIds) {
    return customerInformationService.retrieveCustomersByIds(targetCustomerIds).stream()
        .collect(toConcurrentMap(Customer::getCustomerId, Function.identity()));
  }

  private Map<InteractionType, List<CustomerInteraction>> groupCustomersPerRelationshipType(
      List<CustomerInteraction> network) {
    return network.stream()
        .collect(groupingBy(CustomerInteraction::getInteractionType));
  }

  private List<CustomerNetwork> transformToCustomerNetworks(
      Map<InteractionType, List<CustomerInteraction>> customersPerRelationshipType,
      Map<Integer, Customer> targetCustomers) {
    return customersPerRelationshipType.entrySet().stream()
        .map(entry -> CustomerNetwork.builder()
            .interactionType(entry.getKey())
            .targetCustomer(entry.getValue().stream()
                .map(CustomerInteraction::getTargetCustomerId)
                .map(targetCustomers::get)
                .collect(toUnmodifiableList()))
            .build())
        .collect(toUnmodifiableList());
  }

}
