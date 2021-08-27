package test;

import com.hit.dao.impl.UserDaoImpl;
import com.hit.pojo.User;
import org.junit.Test;

public class UserDaoImplTest {

    private UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {

        if (userDao.queryUserByUsername("admin1") == null) {
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin", "admin1234") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println( userDao.saveUser(new User(null,"wzg168", "123456", "wzg168@qq.com")) );
    }
}