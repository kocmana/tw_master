package at.technikum.masterproject.productinformation.model;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDimension {

  private float width;
  private float height;
  private float depth;
}
