package ra.springsecurityjwt.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.springsecurityjwt.dto.request.ProductRequest;
import ra.springsecurityjwt.dto.response.ResponseDtoSuccess;
import ra.springsecurityjwt.exception.NotFoundException;
import ra.springsecurityjwt.service.product.IProductService;

@RestController
@RequestMapping("/api.com/v5/admin/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;
    // phan quyen theo method
//    @GetMapping("/api.com/v4/products/user")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public ResponseEntity<?> findAllForUser(){
//        return new ResponseEntity<>("ok", HttpStatus.OK);
//    }
//    @PreAuthorize("hasAuthority('ROLE_MOD')")
//    @GetMapping("/api.com/v4/products/mod")
//    public ResponseEntity<?> findAllForMod(){
//        return new ResponseEntity<>("ok", HttpStatus.OK);
//    }
//    @GetMapping("/api.com/v4/products/admin")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<?> findAllForAdmin(){
//        return new ResponseEntity<>("ok", HttpStatus.OK);
//    }
//    @GetMapping("/api.com/v4/products/admin-or-mod")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MOD')")
//    public ResponseEntity<?> findAllForAdminOrMod(){
//        return new ResponseEntity<>("ok", HttpStatus.OK);
//    }

    // danh sách tất cả sp
    @GetMapping
    public ResponseEntity<ResponseDtoSuccess> findAll() {
        return new ResponseEntity<>(new ResponseDtoSuccess(productService.findAllForAdmin(), HttpStatus.OK), HttpStatus.OK);
    }

    // tìm kiê theo id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDtoSuccess> findById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(new ResponseDtoSuccess(productService.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

    // xóa 1 product
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDtoSuccess> deleteById(@PathVariable Long id) throws NotFoundException {
        productService.findById(id);
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // thêm mới sản phâm
    @PostMapping
    public ResponseEntity<?> addProduct(@ModelAttribute @Valid ProductRequest productRequest) {
        return new ResponseEntity<>(new ResponseDtoSuccess(productService.save(productRequest), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    // cập nhật thông tin sp
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@ModelAttribute @Valid ProductRequest productRequest, @PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(new ResponseDtoSuccess(productService.save(productRequest, id), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/toggle-status")
    public ResponseEntity<?> updateProduct(@RequestParam Long id) throws NotFoundException {
        productService.findById(id);
        productService.toggleStatus(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    @GetMapping("/catalogs/{id}")
    public ResponseEntity<?> findPproductByCatalog(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(new ResponseDtoSuccess(productService. findAllNyCatalog(id), HttpStatus.OK), HttpStatus.OK);
    }
}
