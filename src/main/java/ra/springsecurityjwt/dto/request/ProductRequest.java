package ra.springsecurityjwt.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ra.springsecurityjwt.validation.ProductNameUnique;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Không được để trống")
    @NotNull(message = "Không được null")
    @ProductNameUnique(message = "Tên đã tồn tại")
    private String productName;
    @NotNull(message = "Không được null")
    private BigDecimal unitPrice;
    @NotNull(message = "Không được null")
    @Min(value = 10,message = "Giá tiền không được thấp hơn 10")
    private Integer stock;
    private String description;
    @NotNull(message = "Không được null")
    private MultipartFile file;

}
