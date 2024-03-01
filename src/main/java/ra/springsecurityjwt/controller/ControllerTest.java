package ra.springsecurityjwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api.com/v2")
public class ControllerTest {
    @GetMapping("/auth/v1")
    public ResponseEntity<?> publicAll(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @GetMapping("/admin/v1")
    public ResponseEntity<?> admin(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
     @GetMapping("/v3/test")
    public ResponseEntity<?> anyRequest(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
