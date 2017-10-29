package dao.impl;

import dao.UserDao;
import datahelper.DataHelper;
import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class UserDaoImpl implements UserDao {
    @Autowired
    DataHelper dataHelper;

    @Override
    public String login(String name, String password) {
        Connection connection=dataHelper.getConnection();
        Statement statement=dataHelper.getStatement(connection);

        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `user` WHERE name='"+name+"' AND password ='"+password+"' AND verified=1 AND admin=1");
            while (resultSet.next()){
                //结果集不为空
                resultSet.close();
                statement.close();
                connection.close();
                return "admin";
            }
            resultSet.close();
            ResultSet resultSet1=statement.executeQuery("SELECT * FROM `user` WHERE name='"+name+"'  AND verified=1 AND password ='"+password+"'");
            while (resultSet1.next()){
                //结果集不为空
                resultSet1.close();
                statement.close();
                connection.close();
                return "true";
            }
            resultSet1.close();
            statement.close();
            connection.close();
            return "false";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "false";
    }

    @Override
    public UserInfo getUserInfo(String name) {
        Connection connection=dataHelper.getConnection();
        Statement statement=dataHelper.getStatement(connection);
        String zhiye="undefined";
        String dizhi="undefined";
        int age=0;
        int Numguanzhu=0;
        int NumWorks=0;
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `user` WHERE name='"+name+"'");
            while (resultSet.next()){
                zhiye=resultSet.getString("zhiye");
                dizhi=resultSet.getString("dizhi");
                age=resultSet.getInt("age");
                break;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet resultSet=statement.executeQuery("SELECT count(*) FROM `works` WHERE author='"+name+"'");
            while (resultSet.next()){
                NumWorks=resultSet.getInt(1);
                break;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet resultSet=statement.executeQuery("SELECT count(*) FROM `guanzhu` WHERE beiguanzhuzhe='"+name+"'");
            while (resultSet.next()){
                Numguanzhu=resultSet.getInt(1);
                break;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        UserInfo userInfo=new UserInfo(name,zhiye,dizhi,age,Numguanzhu,NumWorks);

        return userInfo;
    }
}
