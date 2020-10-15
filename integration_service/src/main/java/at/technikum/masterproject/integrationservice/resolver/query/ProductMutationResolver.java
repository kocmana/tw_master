package at.technikum.masterproject.integrationservice.resolver.query;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductReviewInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductReviewInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMutationResolver implements GraphQLMutationResolver {

  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;

  @Autowired
  public ProductMutationResolver(ProductInformationClient productInformationClient,
      ProductReviewClient productReviewClient) {
    this.productInformationClient = productInformationClient;
    this.productReviewClient = productReviewClient;
  }

  public int createProduct(CreateProductInput product) {
    return productInformationClient.saveProduct(product);
  }

  public void updateProduct(UpdateProductInput product) {
    productInformationClient.updateProduct(product);
  }

  public void deleteProduct(int productId) {
    productInformationClient.deleteProduct(productId);
  }

  public int createProductReview(CreateProductReviewInput review) {
    return productReviewClient.saveProductReview(review);
  }

  public void updateProductReview(UpdateProductReviewInput review) {
    productReviewClient.updateProductReview(review);
  }

  public void deleteProductReview(int productId) {
    productReviewClient.deleteProductReview(productId);
  }

}
