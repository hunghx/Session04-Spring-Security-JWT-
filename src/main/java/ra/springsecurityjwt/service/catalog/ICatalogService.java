package ra.springsecurityjwt.service.catalog;


import ra.springsecurityjwt.dto.request.ProductRequest;
import ra.springsecurityjwt.dto.response.ProductResponse;
import ra.springsecurityjwt.entity.Catalog;
import ra.springsecurityjwt.entity.Product;
import ra.springsecurityjwt.exception.DataFieldExistException;
import ra.springsecurityjwt.exception.NotFoundException;

import java.util.List;

public interface ICatalogService {
    List<Catalog> findAll();
    Catalog findById(Long id) throws NotFoundException;
    void deleteById(Long id);
    Catalog save(Catalog catalog) throws DataFieldExistException;
    Catalog save(Catalog catalog,Long id);
}
