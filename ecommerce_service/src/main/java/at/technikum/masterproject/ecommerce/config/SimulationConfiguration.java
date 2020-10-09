package at.technikum.masterproject.ecommerce.config;

import at.technikum.masterproject.commons.delay.EnableDelaySimulation;
import at.technikum.masterproject.commons.failure.EnableFailureSimulation;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDelaySimulation
@EnableFailureSimulation
public class SimulationConfiguration {

}
