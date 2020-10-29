package at.technikum.masterproject.benchmarkservice.repository;

import at.technikum.masterproject.benchmarkservice.model.Benchmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenchmarkRepository extends JpaRepository<Benchmark, String> {

}