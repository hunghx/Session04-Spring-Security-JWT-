package ra.springsecurityjwt.service.product;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ra.springsecurityjwt.dto.request.ProductRequest;
import ra.springsecurityjwt.dto.response.ProductResponse;
import ra.springsecurityjwt.entity.Product;
import ra.springsecurityjwt.exception.NotFoundException;
import ra.springsecurityjwt.repository.CatalogRepository;
import ra.springsecurityjwt.repository.ProductRepository;
import ra.springsecurityjwt.service.UploadService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{
    private final UploadService uploadService;
    private final ProductRepository productRepository;
    private final CatalogRepository catalogRepository;
    private final ModelMapper mapper;
    @Override
    public List<Product> findAllForAdmin() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductResponse> findAllForUser() {
        return productRepository.findByStatusIsTrue().stream()
                .map(p->mapper.map(p, ProductResponse.class)).toList();
    }

    @Override
    public Product findById(Long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product save(ProductRequest productRequest) {
         // upload image
        String imageUrl = "https://png.pngtree.com/png-clipart/20220125/original/pngtree-cartoon-hand-drawn-default-avatar-cat-png-image_7227767.png";
        if(productRequest.getFile()!=null && productRequest.getFile().getSize()>0){
            // nếu file tồn tại
            imageUrl = uploadService.uploadFileToServer(productRequest.getFile());
        }
        Product pro  = mapper.map(productRequest,Product.class);
        pro.setImageUrl(imageUrl);
        pro.setStatus(true);
        return productRepository.save(pro);
    }

    @Override
    public Product save(ProductRequest productRequest, Long id) throws NotFoundException {
        // upload image
        Product old = findById(id);
        String imageUrl = old.getImageUrl(); // đường dẫn cũ
        if(productRequest.getFile()!=null &&productRequest.getFile().getSize()>0){
            // nếu file tồn tại
            imageUrl = uploadService.uploadFileToServer(productRequest.getFile());
        }
        Product pro  = mapper.map(productRequest,Product.class);
        pro.setImageUrl(imageUrl);
        pro.setStatus(old.isStatus());
        return productRepository.save(pro);
    }

    @Override
    public void toggleStatus(Long id) {
        productRepository.toggleStatus(id);

    }

    @Override
    public List<Product> findAllNyCatalog(Long catalogId) throws NotFoundException {
        return productRepository.findByCatalog(catalogRepository.
                findById(catalogId).orElseThrow(() -> new NotFoundException("không tìm tấy id")));
    }
}
