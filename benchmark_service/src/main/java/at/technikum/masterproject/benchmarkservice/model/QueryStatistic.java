package at.technikum.masterproject.benchmarkservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class QueryStatistic {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUERY_STATISTIC_ID_GENERATOR")
  @SequenceGenerator(name = "QUERY_STATISTIC_ID_GENERATOR", allocationSize = 1, sequenceName = "query_seq")
  private Integer id;
  @ManyToOne
  private Benchmark benchmark;
  private Long responseTimeInMillis;
}

