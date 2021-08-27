package com.hit.dao.impl;

import com.hit.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Classname QueryRunnerTest
 * @Description TODO
 * @Author DELL
 * @Date 2021/7/5 15:13
 */
/*
commons-dbutils 是 Apache 组织提供的一个开源 JDBC工具类库，它是对JDBC的简单封装，学习成本极低，
并且使用dbutils能极大简化jdbc编码的工作量，同时也不会影响程序的性能。
 */
public abstract class BaseDao {

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * @Author DELL
     * @Description update() 方法用来执行：Insert\Update\Delete 语句
     * @Date 2021/7/22 14:24
     * @Param [connection, sql, args]
     * @Return int 如果返回-1,说明执行失败<br/>返回其他表示影响的行数
     * @Version 1.0
     */
    public int update(String sql, Object ... args){

        Connection connection = JDBCUtils.getConnectionDruid();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * @Author DELL
     * @Description 查询返回一个 javaBean 的 sql 语句
     * @Date 2021/7/22 14:32
     * @Param [type, connection, sql, args]
     * @Return T
     * @Version 1.0
     */
    public <T> T queryForOne(Class<T> type, String sql, Object ... args){
        Connection connection = JDBCUtils.getConnectionDruid();
        try {
            //BeanHandler:是ResultSetHandler接口的实现类，用于封装表中的一条记录
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author DELL
     * @Description 查询返回多个 javaBean 的 sql 语句
     * @Date 2021/7/22 14:37
     * @Param [type, connection, sql, args]
     * @Return java.util.List<T>
     * @Version 1.0
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ... args){
        Connection connection = JDBCUtils.getConnectionDruid();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author DELL
     * @Description 执行返回一行一列的 sql 语句查询结果
     * @Date 2021/7/22 14:40
     * @Param [connection, sql, args]
     * @Return java.lang.Object
     * @Version 1.0
     */
    public Long queryForSingleValue(String sql, Object ... args){
        Connection connection = JDBCUtils.getConnectionDruid();
        try {
            ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
            return queryRunner.query(connection, sql, scalarHandler, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author DELL
     * @Description   执行返回一行一列的 sql 语句查询结果,将字段及相应字段的值作为map中的key和value
     * @Date 2021/7/22 14:48
     * @Param [connection, sql, args]
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     * @Version 1.0
     */
    public Map<String,Object> queryForMap(String sql, Object ... args){
        Connection connection = JDBCUtils.getConnectionDruid();
        try {
            return queryRunner.query(connection, sql, new MapHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author DELL
     * @Description  执行返回多个一行一列的 sql 语句查询结果,将字段及相应字段的值作为map中的key和value
     * @Date 2021/7/22 14:50
     * @Param [connection, sql, args]
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Version 1.0
     */
    public List<Map<String,Object>> queryForMapList(String sql, Object ... args){
        Connection connection = JDBCUtils.getConnectionDruid();
        try {
            return queryRunner.query(connection, sql, new MapListHandler(), 22);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
