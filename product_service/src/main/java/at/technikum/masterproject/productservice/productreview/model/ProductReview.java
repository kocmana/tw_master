package at.technikum.masterproject.productservice.productreview.model;

import at.technikum.masterproject.productservice.productinformation.model.Product;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_review")
@Getter
@Setter
@NoArgsConstructor
public class ProductReview {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int customerId;
  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;
  private int stars;
  private String review;
}
