package test;

import com.hit.pojo.User;
import com.hit.service.UserService;
import com.hit.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    private UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "bbj168", "666666", "bbj168@qq.com"));
        userService.registerUser(new User(null, "abc168", "666666", "abc168@qq.com"));
    }

    @Test
    public void loginIn() {
        System.out.println(userService.login(new User(null, "wzg168", "123456", "wzg168@qq.com")));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("wzg168")){
            System.out.println("用户名已经存在");
        }else{
            System.out.println("用户名可用");
        }
    }
}