package datahelper.Impl;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.stereotype.Service;
import datahelper.DataHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by moontell on 2017/5/15.
 */

/**
 * tomcat数据库连接池配置
 * 使用getInstance获取实例
 * 使用getConnection获取数据库链接
 */
@Service
public class DataHelperImpl implements DataHelper{
    private DataSource dataSource;
    public DataHelperImpl(){
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://115.159.27.19:3306/pome");//数据库的url
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("pome");//用户名
        p.setPassword("pome");//密码
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                return dataSource.getConnection();
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    return dataSource.getConnection();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                    try {
                        return dataSource.getConnection();
                    } catch (SQLException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Statement getStatement(Connection connection){
        //Connection connection=getConnection();
        Statement statement=null;
        int i=0;
        while(i<5){
            try {
                statement=connection.createStatement();
                if(statement!=null)
                    return statement;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
