package at.technikum.masterproject.customerservice.customernetwork;

import at.technikum.masterproject.customerservice.customernetwork.model.domain.CustomerInteraction;
import at.technikum.masterproject.customerservice.customernetwork.model.domain.CustomerInteractionId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerNetworkRepository extends JpaRepository<CustomerInteraction, CustomerInteractionId> {

  List<CustomerInteraction> findBySourceCustomerId(Integer sourceCustomerId);

}
