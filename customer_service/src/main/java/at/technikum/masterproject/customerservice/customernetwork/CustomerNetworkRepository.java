package at.technikum.masterproject.customerservice.customernetwork;

import at.technikum.masterproject.customerservice.customernetwork.model.CustomerInteraction;
import at.technikum.masterproject.customerservice.customernetwork.model.CustomerInteraction.CustomerRelationshipId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerNetworkRepository extends JpaRepository<CustomerInteraction, CustomerRelationshipId> {

  List<CustomerInteraction> findByIdSourceCustomerId(Integer sourceCustomerId);

}
