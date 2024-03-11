package ra.springsecurityjwt.service.product;

import ra.springsecurityjwt.dto.request.ProductRequest;
import ra.springsecurityjwt.dto.response.ProductResponse;
import ra.springsecurityjwt.entity.Product;
import ra.springsecurityjwt.exception.NotFoundException;

import java.util.List;

public interface IProductService {
    List<Product> findAllForAdmin();
    List<Product> findAllNyCatalog(Long catalogId) throws NotFoundException;
    List<ProductResponse> findAllForUser();
    Product findById(Long id) throws NotFoundException;
    void deleteById(Long id);
    Product save(ProductRequest productRequest);
    Product save(ProductRequest productRequest,Long id) throws NotFoundException;
    void toggleStatus(Long id);

}
