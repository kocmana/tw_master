package at.technikum.masterproject.productinformation;

import at.technikum.masterproject.productinformation.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductInformationRepository extends JpaRepository<Product, Integer> {

}
