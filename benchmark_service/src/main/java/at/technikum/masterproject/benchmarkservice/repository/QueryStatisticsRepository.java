package at.technikum.masterproject.benchmarkservice.repository;

import at.technikum.masterproject.benchmarkservice.model.QueryStatistic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryStatisticsRepository extends JpaRepository<QueryStatistic, Integer> {

  List<QueryStatistic> findAllByBenchmarkUuid(String uuid);

}
