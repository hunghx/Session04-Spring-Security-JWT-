package ra.springsecurityjwt.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.springsecurityjwt.entity.Catalog;
import ra.springsecurityjwt.entity.Product;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByStatusIsTrue();
    @Query(value = "update Product set status = not status where id= :id",nativeQuery = true)
    @Modifying
    void toggleStatus(@Param("id") Long id);

    boolean  existsByProductName(String productName);

    List<Product> findByCatalog(Catalog catalog);

}
