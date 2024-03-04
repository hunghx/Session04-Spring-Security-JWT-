package ra.springsecurityjwt;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ra.springsecurityjwt.entity.Role;
import ra.springsecurityjwt.entity.RoleName;
import ra.springsecurityjwt.repository.RoleRepository;

@SpringBootApplication
public class SpringSecurityJwtApplication {
    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }
//    @Bean
//    public CommandLineRunner runner(RoleRepository roleRepository){
//        return args -> {
//            // thực  hiện thêm dữ liệu  mẫu ở đây
//            Role admin = new Role(1L, RoleName.ROLE_ADMIN);
//            Role user = new Role(2L, RoleName.ROLE_USER);
//            Role mod = new Role(3L, RoleName.ROLE_MOD);
//            roleRepository.save(admin);
//            roleRepository.save(user);
//            roleRepository.save(mod);
//        };
//    }

}
