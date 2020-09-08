package at.technikum.masterproject.customer;

import at.technikum.masterproject.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CustomerInformationRepository extends JpaRepository<Customer, Integer> {

}
