package at.technikum.masterproject.ecommerceservice.purchase;

import at.technikum.masterproject.ecommerceservice.purchase.model.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Integer> {

}
