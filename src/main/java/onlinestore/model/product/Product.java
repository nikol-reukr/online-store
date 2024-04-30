package onlinestore.model.product;

import lombok.*;
import onlinestore.model.product.Measure;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String manufacturer;
    private Measure measure;
}

