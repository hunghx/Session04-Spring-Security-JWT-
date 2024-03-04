package ra.springsecurityjwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    // phan quyen theo method
    @GetMapping("/api.com/v4/products/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> findAllForUser(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ROLE_MOD')")
    @GetMapping("/api.com/v4/products/mod")
    public ResponseEntity<?> findAllForMod(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @GetMapping("/api.com/v4/products/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> findAllForAdmin(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @GetMapping("/api.com/v4/products/admin-or-mod")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MOD')")
    public ResponseEntity<?> findAllForAdminOrMod(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
