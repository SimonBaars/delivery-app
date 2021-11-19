package delivery.app.common;

import delivery.app.common.security.web.client.Interceptor;
import delivery.app.common.security.web.client.JWTHeadersClientRequestCustomizer;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BaseConfiguration {

  @Bean
  public JWTHeadersClientRequestCustomizer clientInternalJWTRequestCustomizer() {
    return new JWTHeadersClientRequestCustomizer();
  }

  @Bean
  public WebClientCustomizer clientInternalJWTRequestCustomizerWebClient() {
    return b -> WebClient.builder().filter(new Interceptor());
  }
}
