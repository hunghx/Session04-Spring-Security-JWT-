package ra.springsecurityjwt.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.springsecurityjwt.dto.request.ProductRequest;
import ra.springsecurityjwt.dto.response.ResponseDtoSuccess;
import ra.springsecurityjwt.entity.Catalog;
import ra.springsecurityjwt.exception.DataFieldExistException;
import ra.springsecurityjwt.exception.NotFoundException;
import ra.springsecurityjwt.service.catalog.ICatalogService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api.com/v5/admin/catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private  final ICatalogService catalogService;
    // danh sách tất cả sp
    @GetMapping
    public ResponseEntity<ResponseDtoSuccess> findAll() {
        return new ResponseEntity<>(new ResponseDtoSuccess(catalogService.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    // tìm kiêm theo id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDtoSuccess> findById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(new ResponseDtoSuccess(catalogService.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

    // xóa 1 product
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDtoSuccess> deleteById(@PathVariable Long id) throws NotFoundException {
        catalogService.findById(id); // kiểm tra tồn tai
        catalogService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // thêm mới sản phâm
    @PostMapping
    public ResponseEntity<?> addCatalog(@RequestBody @Valid Catalog catalog) throws DataFieldExistException {
        return new ResponseEntity<>(new ResponseDtoSuccess(catalogService.save(catalog), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    // cập nhật thông tin sp
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCatalog(@RequestBody @Valid Catalog catalog, @PathVariable Long id) throws NotFoundException, DataFieldExistException {
        catalogService.findById(id); // kiểm tra tồn tại
        Catalog cat;
        try{
           cat =  catalogService.save(catalog, id);
        }catch (Exception e){
            Map<String, String> map = new HashMap<>();
            map.put("catalogName","catalogName is exists !");
            throw  new DataFieldExistException(map);
        }
        return new ResponseEntity<>(new ResponseDtoSuccess(cat, HttpStatus.OK), HttpStatus.OK);
    }
}
