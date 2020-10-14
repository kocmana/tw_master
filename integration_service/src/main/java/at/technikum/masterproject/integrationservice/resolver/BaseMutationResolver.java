package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductReviewInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductReviewInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class BaseMutationResolver implements GraphQLMutationResolver {

  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;

  public BaseMutationResolver(ProductInformationClient productInformationClient,
      ProductReviewClient productReviewClient) {
    this.productInformationClient = productInformationClient;
    this.productReviewClient = productReviewClient;
  }

  public Integer createProduct(CreateProductInput product) {
    return productInformationClient.saveProduct(product);
  }

  public void updateProduct(UpdateProductInput product) {
    productInformationClient.updateProduct(product);
  }

  public Integer createProductReview(CreateProductReviewInput review) {
    return productReviewClient.saveProductReview(review);
  }

  public void updateProductReview(UpdateProductReviewInput review) {
    productReviewClient.updateProductReview(review);
  }


}
