package at.technikum.masterproject.productservice.productreview;

import at.technikum.masterproject.productservice.productreview.model.ProductReview;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {

  List<ProductReview> findByProductId(int productId);

  List<ProductReview> findByCustomerId(int customerId);
}
