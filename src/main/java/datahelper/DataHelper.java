package datahelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by moontell on 2017/5/15.
 */

/**
 * tomcat数据库连接池配置
 * 使用getInstance获取实例
 * 使用getConnection获取数据库链接
 */
public interface DataHelper {
    public Connection getConnection();
    public Statement getStatement(Connection connection);
    public void close( ResultSet resultSet,Statement statement, Connection connection);
}
