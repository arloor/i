package crawler;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import java.sql.*;
import java.util.List;

/**
 * Created by moontell on 2017/6/6.
 * 爬虫模块 数据持久化
 */
public class DataHelper {
    static String url = "jdbc:mysql://122.152.197.205:3306/moontell?user=moontell&password=!@MOONtell426543&useUnicode=true&characterEncoding=UTF8";


    Connection conn;

    public DataHelper() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("mysql驱动缺失");
        }
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //测试连接
    public static void main(String[] args) {
        new DataHelper();
    }

    /**
     *保存到数据库
     * @param contentList 新闻内容的链表
     */

    public void save(List<String[]> contentList) {
        //这个模块被我们单独做成一个jar包，在服务器上使用crontab定时运行，来更新新闻内容
        //所以这部分代码的含义是：
        //如果news数据库存在，首先删除这张表（清空旧数据）
        //然后新建news表
        //最后循环写入数据
        try {
            Statement statement = conn.createStatement();
            String sql;
            sql = "drop TABLE  if exists `stock_news`;";
            System.out.println(sql);
            statement.executeUpdate(sql);
            sql = "create table stock_news" +
                    "(" +
                    "  id int auto_increment" +
                    "    primary key," +
                    "  title varchar(255) not null," +
                    "  time varchar(255) not null," +
                    "  article longtext not null" +
                    ")" +
                    ";";
            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
            PreparedStatement preparedStatement = conn.prepareStatement("");
            for (String[] content : contentList
                    ) {
                sql = "INSERT INTO stock_news(title, time, article) VALUE ('" + content[0] + "','" + content[1] + "','" + content[2] + "');";
                System.out.println(sql);
                preparedStatement = conn.prepareStatement(sql);
                try {
                    preparedStatement.executeUpdate(sql);
                } catch (MySQLSyntaxErrorException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            preparedStatement.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
