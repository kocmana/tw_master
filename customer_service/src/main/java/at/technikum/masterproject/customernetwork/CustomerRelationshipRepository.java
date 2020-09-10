package at.technikum.masterproject.customernetwork;

import at.technikum.masterproject.customernetwork.model.CustomerRelationship;
import at.technikum.masterproject.customernetwork.model.CustomerRelationship.CustomerRelationshipId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRelationshipRepository extends JpaRepository<CustomerRelationship, CustomerRelationshipId> {

  List<CustomerRelationship> findByIdSourceCustomerId(Integer sourceCustomerId);

}
