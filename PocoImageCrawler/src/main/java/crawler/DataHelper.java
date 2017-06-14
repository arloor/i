package crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Map;

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
     * @param linkAndImgSrcMap 新闻内容的链表
     */

    public void save(Map<String,String> linkAndImgSrcMap) {

        //下载文件
        File file= new File("pocoImg");
        if(!file.exists()){
            file.mkdir();
        }

        for (Map.Entry<String,String> entry:linkAndImgSrcMap.entrySet()
             ) {
            String imgSrc=entry.getValue();
            String imgName="pocoImg"+imgSrc.substring(imgSrc.lastIndexOf("/"),imgSrc.lastIndexOf("?"));
            try {
                //下载图片
                URL uri = new URL(imgSrc);
                InputStream in = uri.openStream();
                FileOutputStream fo = new FileOutputStream(new File(imgName));
                byte[] buf = new byte[1024];
                int length = 0;
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
                System.out.println(imgName+"下载完成");
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }

        }


        //这个模块被我们单独做成一个jar包，在服务器上使用crontab定时运行，来更新新闻内容
        //所以这部分代码的含义是：
        //如果news数据库存在，首先删除这张表（清空旧数据）
        //然后新建news表
        //最后循环写入数据
        try {
            Statement statement = conn.createStatement();
            String sql;
            sql = "drop TABLE  if exists `poco_img`;";
            System.out.println(sql);
            statement.executeUpdate(sql);
            sql = "create table poco_img" +
                    "(" +
                    "  id int auto_increment" +
                    "    primary key," +
                    "  link varchar(255) not null," +
                    "  img_src longtext not null" +
                    ")" +
                    ";";
            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
            PreparedStatement preparedStatement = conn.prepareStatement("");
            for (Map.Entry<String,String>  entry: linkAndImgSrcMap.entrySet()
                    ) {
                String imgSrc=entry.getValue();
                String imgName="pocoImg"+imgSrc.substring(imgSrc.lastIndexOf("/"),imgSrc.lastIndexOf("?"));
                sql = "INSERT INTO poco_img(link, img_src) VALUE ('" + entry.getKey() + "','" + imgName + "');";
                System.out.println(sql);
                preparedStatement = conn.prepareStatement(sql);
                try {
                    preparedStatement.executeUpdate(sql);
                } catch (com.mysql.jdbc.exceptions.MySQLSyntaxErrorException e) {
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
