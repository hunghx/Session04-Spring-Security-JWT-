package ra.springsecurityjwt.service.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ra.springsecurityjwt.dto.request.ProductRequest;
import ra.springsecurityjwt.entity.Catalog;
import ra.springsecurityjwt.exception.DataFieldExistException;
import ra.springsecurityjwt.exception.NotFoundException;
import ra.springsecurityjwt.repository.CatalogRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CatalogService implements ICatalogService{
    private final CatalogRepository catalogRepository;
    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public Catalog findById(Long id) throws NotFoundException {
        return catalogRepository.findById(id).orElseThrow(() -> new NotFoundException("Catalog not found"));
    }

    @Override
    public void deleteById(Long id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public Catalog save(Catalog catalog) throws DataFieldExistException {
        if (catalogRepository.existsByCatalogName(catalog.getCatalogName())){
            Map<String, String> map = new HashMap<>();
            map.put("catalogName","catalogName is exists !");
            throw  new DataFieldExistException(map);
        }
        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog save(Catalog catalog, Long id) {
        catalog.setId(id);
        return catalogRepository.save(catalog);
    }

}
