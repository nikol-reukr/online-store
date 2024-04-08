package onlinestore.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Product {
    private long id;
    private String name;
    private int quantity;
    private float price;
}

