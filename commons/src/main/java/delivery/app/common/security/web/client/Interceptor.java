package delivery.app.common.security.web.client;

import static delivery.app.common.security.web.client.JWTSecurityHeaders.addJWTBearerToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

public class Interceptor implements ExchangeFilterFunction {
  @Override
  public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
    addJWTBearerToken(request.headers(), SecurityContextHolder.getContext().getAuthentication());
    return next.exchange(request);
  }
}
