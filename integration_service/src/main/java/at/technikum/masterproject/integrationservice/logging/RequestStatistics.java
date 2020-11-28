package at.technikum.masterproject.integrationservice.logging;

import lombok.Data;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestStatistics {

  private StopWatch stopWatch = new StopWatch();
  private List<String> downstreamRequestIds = new ArrayList<String>();
}
