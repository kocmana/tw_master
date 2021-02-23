package at.technikum.masterproject.productservice.productinformation.model.domain;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ProductDimension {

  private float width;
  private float height;
  private float depth;
}
