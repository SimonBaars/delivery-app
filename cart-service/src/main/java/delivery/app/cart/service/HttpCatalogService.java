package delivery.app.cart.service;

import static java.util.stream.Collectors.toList;

import delivery.app.user.CatalogServiceApi;
import delivery.app.user.dto.Product;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HttpCatalogService implements CatalogServiceApi {

  final WebClient webClient;

  public HttpCatalogService(WebClient.Builder webClientBuilder) {
    this.webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
  }

  @Override
  public Product find(String productId) {
    return webClient.get().uri("/api/products/{id}", productId).retrieve().bodyToMono(Product.class).block();
  }

  @Override
  public List<Product> findAll() {
    return webClient.get().uri("/api/products").retrieve().bodyToFlux(Product.class).collect(toList()).block();
  }

  @Override
  public boolean exist(String productId) {
    return Boolean.TRUE.equals(webClient
        .head()
        .uri("/api/products/{id}", productId)
        .exchangeToMono(res -> {
          res.releaseBody();
          return Mono.just(res.statusCode().is2xxSuccessful());
        })
        .block());
  }

  @Override
  public void update(String productId, Product product) {

  }
}
