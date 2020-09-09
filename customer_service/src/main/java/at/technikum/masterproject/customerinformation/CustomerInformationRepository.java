package at.technikum.masterproject.customerinformation;

import at.technikum.masterproject.customerinformation.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CustomerInformationRepository extends JpaRepository<Customer, Integer> {

}
