package at.technikum.masterproject.integrationservice.resolver.async.mutation;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductReviewInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductReviewInput;
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

  public CompletableFuture<Integer> createProduct(CreateProductInput product) {
    return productInformationClient.saveProduct(product)
        .toFuture();
  }

  public void updateProduct(UpdateProductInput product) {
    productInformationClient.updateProduct(product);
  }

  public void deleteProduct(int productId) {
    productInformationClient.deleteProduct(productId);
  }

  public CompletableFuture<Integer> createProductReview(CreateProductReviewInput review) {
    return productReviewClient.saveProductReview(review)
        .toFuture();
  }

  public void updateProductReview(UpdateProductReviewInput review) {
    productReviewClient.updateProductReview(review);
  }

  public void deleteProductReview(int productId) {
    productReviewClient.deleteProductReview(productId);
  }

}
