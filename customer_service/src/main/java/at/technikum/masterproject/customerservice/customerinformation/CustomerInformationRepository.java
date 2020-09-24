package at.technikum.masterproject.customerservice.customerinformation;

import at.technikum.masterproject.customerservice.customerinformation.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CustomerInformationRepository extends JpaRepository<Customer, Integer> {

}
