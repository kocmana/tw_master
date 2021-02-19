package at.technikum.masterproject.customerservice.customernetwork;

import at.technikum.masterproject.customerservice.customernetwork.model.entity.CustomerInteractionEntity;
import at.technikum.masterproject.customerservice.customernetwork.model.entity.CustomerInteractionEntity.CustomerRelationshipId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerNetworkRepository extends JpaRepository<CustomerInteractionEntity, CustomerRelationshipId> {

  List<CustomerInteractionEntity> findByIdSourceCustomerId(Integer sourceCustomerId);

}
