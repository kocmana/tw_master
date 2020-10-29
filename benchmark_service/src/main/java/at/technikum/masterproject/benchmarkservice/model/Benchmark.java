package at.technikum.masterproject.benchmarkservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Benchmark {

  @Id
  String uuid;
  String schema;
  @Column(name = "number_of_calls")
  int numberOfCalls;
  @Setter
  boolean finished;
}

