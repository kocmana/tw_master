package at.technikum.masterproject.ecommerce.price;

import at.technikum.masterproject.ecommerce.price.model.Price;
import at.technikum.masterproject.ecommerce.price.model.PriceId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface PriceRepository extends JpaRepository<Price, PriceId> {

  List<Price> findAllByProductIdOrderByValidFrom(Integer productId);

  @Query("select p from Price p "
      + "where p.productId = :productId and :dateTime between p.validFrom and p.validTo")
  Optional<Price> findPriceByProductIdThatIsValidAtDateTime(Integer productId, LocalDateTime dateTime);

  @Query("select p from Price p "
      + "where p.productId = :productId and "
      + "((p.validFrom <= :timeFrom and p.validTo >= :timeFrom) or "
      + "(p.validFrom >= :timeFrom and p.validFrom <= :timeTo))"
      + "order by p.validFrom asc")
  List<Price> getPricesByProductIdValidInTimeframe(Integer productId, LocalDateTime timeFrom,
      LocalDateTime timeTo);

}
