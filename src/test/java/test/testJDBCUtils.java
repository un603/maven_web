package test;

import com.hit.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Classname testJDBCUtils
 * @Description TODO
 * @Author DELL
 * @Date 2021/7/22 14:12
 */
public class testJDBCUtils {

    @Test
    public void test1() throws Exception {

        Connection connectionDruid = JDBCUtils.getConnectionDruid();

        System.out.println("connectionDruid = " + connectionDruid);

        JDBCUtils.closeResource(connectionDruid,null);

    }
}
