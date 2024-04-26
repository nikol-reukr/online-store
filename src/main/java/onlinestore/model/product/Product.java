package onlinestore.model.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import onlinestore.model.product.Measure;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
public class Product {
    private long id;
    private String name;
    private BigDecimal price;
    private String manufacturer;
    private Measure measure;
}

