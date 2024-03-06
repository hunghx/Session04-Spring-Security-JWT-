package ra.springsecurityjwt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    // sku
    private String productName;
    @Column(precision = 10, scale = 2) // Decimal(10,2)
    private BigDecimal unitPrice;
    private Integer stock;
    @Column(columnDefinition = "text")
    private String description;
    private String imageUrl;
    private boolean status;
}
