package at.technikum.master_project.productinformation;

import at.technikum.master_project.productinformation.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductInformationsRepository extends JpaRepository<Product, Integer> {

}
