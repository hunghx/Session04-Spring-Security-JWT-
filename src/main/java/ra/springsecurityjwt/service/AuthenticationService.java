package ra.springsecurityjwt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.springsecurityjwt.dto.request.SignInRequest;
import ra.springsecurityjwt.dto.request.SignUpRequest;
import ra.springsecurityjwt.dto.response.SignInDtoResponse;
import ra.springsecurityjwt.entity.Role;
import ra.springsecurityjwt.entity.RoleName;
import ra.springsecurityjwt.entity.User;
import ra.springsecurityjwt.exception.DataFieldExistException;
import ra.springsecurityjwt.exception.UsernameOrPasswordException;
import ra.springsecurityjwt.repository.RoleRepository;
import ra.springsecurityjwt.repository.UserRepository;
import ra.springsecurityjwt.security.jwt.JwtProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    public void signUp(SignUpRequest signUpRequest) throws DataFieldExistException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())){
            throw new DataFieldExistException("username is exist");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new DataFieldExistException("email is exist");
        }
        User user = mapper.map(signUpRequest, User.class); // chuyển dổi
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword())); // mã hóa mật khâu
        user.setStatus(true);
        Role roleUser = roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Role not exisst"));
        user.setRoleSet(new HashSet<>());
        user.getRoleSet().add(roleUser);
        userRepository.save(user);
    };
    public SignInDtoResponse signIn(SignInRequest signInRequest) throws UsernameOrPasswordException {
       // xác thực thông qua username và password
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        }catch (AuthenticationException e){
            log.error("username or pass incorrect : ",e.getMessage());
            throw new UsernameOrPasswordException("username hoặc mật khẩu khong chính xác");
        }
        // xaác thực thành công
        User user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("not found"));
        // lấy ra userDetail
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // tao token
        String accessToken = jwtProvider.generateToken(userDetails);
        // chuyển danh sách quyền Set<Role> -> List<String>
        List<String> roles = user.getRoleSet().stream().map(r->r.getRoleName().name()).toList();
        return SignInDtoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .accessToken(accessToken)
                .birthDay(user.getBirthDay())
                .role(roles).build();

    };
}
