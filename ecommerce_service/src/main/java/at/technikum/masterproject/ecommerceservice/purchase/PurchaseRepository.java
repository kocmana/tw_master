package at.technikum.masterproject.ecommerceservice.purchase;

import at.technikum.masterproject.ecommerceservice.purchase.model.domain.Purchase;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

  List<Purchase> findPurchasesByCustomerId(Integer customerId);
}
