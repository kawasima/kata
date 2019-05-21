package kata.ex02.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {
    private Long productNo;
    private String name;
}
