package at.technikum.masterproject.ecommerce.purchase;

import static java.util.stream.Collectors.toUnmodifiableList;

import at.technikum.masterproject.ecommerce.purchase.model.Purchase;
import at.technikum.masterproject.ecommerce.purchase.model.dto.PurchaseDto;
import at.technikum.masterproject.ecommerce.purchase.model.mapper.PurchaseMapper;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/purchase")
public class PurchaseController {

  private final PurchaseService purchaseService;
  private final PurchaseMapper purchaseMapper;

  @Autowired
  public PurchaseController(PurchaseService purchaseService,
      PurchaseMapper purchaseMapper) {
    this.purchaseService = purchaseService;
    this.purchaseMapper = purchaseMapper;
  }

  @GetMapping("/{id}")
  public ResponseEntity<PurchaseDto> retrievePurchaseById(@PathVariable @Valid @NotNull long id) {
    Purchase purchase = purchaseService.getPurchaseById(id);
    return ResponseEntity.ok(purchaseMapper.purchaseToPurchaseDto(purchase));
  }

  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<PurchaseDto>> retrievePurchasesByCustomerId(
      @PathVariable @Valid @NotNull Integer customerId) {
    List<PurchaseDto> purchaseDtos = purchaseService.getPurchasesForCustomer(customerId)
        .stream()
        .map(purchaseMapper::purchaseToPurchaseDto)
        .collect(toUnmodifiableList());
    return ResponseEntity.ok(purchaseDtos);
  }

  @PostMapping
  public ResponseEntity<Long> savePurchase(@RequestBody @Valid PurchaseDto purchaseDto) {
    purchaseDto.setId(null);
    Long newPurchaseId = purchaseService
        .savePurchase(purchaseMapper.purchaseDtoToPurchase(purchaseDto));
    return ResponseEntity.ok(newPurchaseId);
  }
}
