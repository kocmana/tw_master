package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInteractionInput;
import java.util.List;

public interface CustomerNetworkClient {

  List<CustomerNetwork> getNetworkById(int customerId);

  CustomerInteraction saveCustomerInteraction(CreateCustomerInteractionInput customerInteraction);
}
