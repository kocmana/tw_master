package at.technikum.masterproject.price;

import at.technikum.masterproject.price.model.Price;
import at.technikum.masterproject.price.model.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PriceRepository extends JpaRepository<Price, PriceId> {

}
