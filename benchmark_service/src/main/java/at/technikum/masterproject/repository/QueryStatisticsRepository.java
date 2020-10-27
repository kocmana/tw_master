package at.technikum.masterproject.repository;

import at.technikum.masterproject.model.QueryStatistic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryStatisticsRepository extends JpaRepository<QueryStatistic, Integer> {

  List<QueryStatistic> findAllBySchema(String schema);

}
