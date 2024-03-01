package ra.springsecurityjwt;

import ra.springsecurityjwt.entity.RoleName;

public class Test {
    public static void main(String[] args) {
        RoleName roleName =  RoleName.ROLE_ADMIN;
        System.out.println(roleName.name());
    }
}
