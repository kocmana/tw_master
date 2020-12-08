package at.technikum.masterproject.integrationservice.logging.model;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StopWatch;

@NoArgsConstructor
@ToString
public class RequestStatistics {

  private final StopWatch stopWatch = new StopWatch();
  @Getter
  private final Queue<DownstreamRequest> downstreamRequests = new ConcurrentLinkedQueue<>();

  public final void startRequest() {
    stopWatch.start();
  }

  public final void stopRequest() {
    stopWatch.stop();
  }

  public final long getRequestDuration() {
    return stopWatch.getLastTaskTimeMillis();
  }

  public final int getNumberOfDownstreamRequests() {
    return downstreamRequests.size();
  }

  public final void addDownstreamRequest(DownstreamRequest downstreamRequest) {
    downstreamRequests.add(downstreamRequest);
  }

  public final String toString() {
    StringBuilder reportBuilder = new StringBuilder(generateBasicReport());
    downstreamRequests.forEach(downstreamRequest -> reportBuilder
        .append("\n\t")
        .append(downstreamRequest.toString()));
    return reportBuilder.toString();
  }

  public final String generateBasicReport() {
    return String.format("Requests: %d %n Duration: %d ms",
        downstreamRequests.size(),
        stopWatch.getTotalTimeMillis());
  }
}
