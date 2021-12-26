package science.monke.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HttpNotFound extends ResponseStatusException {
  public HttpNotFound() {
    super(HttpStatus.NOT_FOUND);
  }

  public HttpNotFound(final String reason) {
    super(HttpStatus.NOT_FOUND, reason);
  }

  public HttpNotFound(final String reason, final Throwable cause) {
    super(HttpStatus.NOT_FOUND, reason, cause);
  }
}
