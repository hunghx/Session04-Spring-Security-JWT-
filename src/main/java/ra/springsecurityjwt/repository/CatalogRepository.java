package ra.springsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import ra.springsecurityjwt.entity.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {
    boolean existsByCatalogName(String name);
}
