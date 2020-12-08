package at.technikum.masterproject.integrationservice.logging;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class LoggingConstants {

  static final String REQUEST_ID_KEY = "X-Request-ID";
  static final String CORRELATION_ID = "correlation_id";

}
