package at.technikum.masterproject.integrationservice.resolver.async.mutation;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductReviewInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductReviewInput;
import at.technikum.masterproject.integrationservice.resolver.async.ResolverExecutor;
import graphql.kickstart.tools.GraphQLMutationResolver;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "ASYNC")
@RequiredArgsConstructor
public class AsyncProductMutationResolver implements GraphQLMutationResolver {

  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<Integer> createProduct(CreateProductInput product) {
    return resolverExecutor.resolve(() -> productInformationClient.saveProduct(product));
  }

  public void updateProduct(UpdateProductInput product) {
    resolverExecutor.run(() -> productInformationClient.updateProduct(product));
  }

  public void deleteProduct(int productId) {
    resolverExecutor.run(() -> productInformationClient.deleteProduct(productId));
  }

  public CompletableFuture<Integer> createProductReview(CreateProductReviewInput review) {
    return resolverExecutor.resolve(() -> productReviewClient.saveProductReview(review));
  }

  public void updateProductReview(UpdateProductReviewInput review) {
    resolverExecutor.run(() -> productReviewClient.updateProductReview(review));
  }

  public void deleteProductReview(int productId) {
    resolverExecutor.run(() -> productReviewClient.deleteProductReview(productId));
  }

}
