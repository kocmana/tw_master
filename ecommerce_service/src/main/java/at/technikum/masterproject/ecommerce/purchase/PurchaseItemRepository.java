package at.technikum.masterproject.ecommerce.purchase;

import at.technikum.masterproject.ecommerce.purchase.model.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

}
