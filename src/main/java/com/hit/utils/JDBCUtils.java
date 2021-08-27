package com.hit.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Classname JDBCUtils
 * @Description Druid数据库连接池
 * @Author DELL
 * @Date 2021/7/5 14:14
 */
public class JDBCUtils {

    private static DataSource druidDataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();

            FileInputStream is = new FileInputStream("E:\\maven_web\\src\\main\\resources\\dev\\Druid.properties");
//            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("Druid1.properties");
//            assert is != null;
            properties.load(is);

            druidDataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author DELL
     * @Description  使用Druid数据库连接池获取数据库的连接
     * @Date 2021/7/5 15:05
     * @Param []
     * @Return java.sql.Connection
     * @Version 1.0
     */
    public static Connection getConnectionDruid(){
        System.out.println("hello world");
        Connection connection = conns.get();
        if(connection == null){
            try {
                connection = druidDataSource.getConnection();

                conns.set(connection);//保存到ThreadLocal中，供后面的JDBC操作使用

                connection.setAutoCommit(false);//设置为手动管理事务


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    /**
     * @Author DELL
     * @Description  提交事务，并关闭释放连接
     * @Date 2021/8/3 15:28
     * @Param []
     * @Return void
     * @Version 1.0
     */
    public static void commitAndClose(){
        Connection connection = conns.get();

        if (connection != null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DbUtils.closeQuietly(connection);
            }
        }

        //一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池）
        conns.remove();
    }

    /**
     * @Author DELL
     * @Description  回滚事务，并关闭释放连接
     * @Date 2021/8/3 15:28
     * @Param []
     * @Return void
     * @Version 1.0
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();

        if (connection != null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DbUtils.closeQuietly(connection);
            }
        }

        //一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池）
        conns.remove();
    }

    /**
     * @Author DELL
     * @Description  关闭连接和Statement的操作
     * @Date 2021/6/29 13:17
     * @Param [connection, ps]
     * @Return void
     * @Version 1.0
     */
    public static void closeResource(Connection connection, Statement ps){
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(ps);
    }

    /**
     * @Author DELL
     * @Description  关闭资源的操作
     * @Date 2021/6/29 15:36
     * @Param [connection, ps, rs]
     * @Return void
     * @Version 1.0
     */
    public static void closeResource(Connection connection, Statement ps, ResultSet rs) {
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);
    }
}
