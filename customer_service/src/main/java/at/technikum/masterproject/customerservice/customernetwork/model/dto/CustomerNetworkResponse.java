package at.technikum.masterproject.customerservice.customernetwork.model.dto;

import at.technikum.masterproject.customerservice.customerinformation.model.dto.CustomerResponse;
import at.technikum.masterproject.customerservice.customernetwork.model.InteractionType;
import java.util.List;
import lombok.Data;


@Data
public class CustomerNetworkResponse {

  private InteractionType interactionType;
  private List<CustomerResponse> targetCustomer;
}
