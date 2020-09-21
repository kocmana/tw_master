package at.technikum.masterproject.purchase;

import at.technikum.masterproject.purchase.model.Purchase;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  List<Purchase> findPurchasesByCustomerId(Integer customerId);
}
