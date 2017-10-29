package dao.impl;

import dao.WorksDao;
import datahelper.DataHelper;
import domain.Works;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Service
public class WorksDaoImpl implements WorksDao {
    @Autowired
    DataHelper dataHelper;

    @Override
    public List<Works> getWorks(String name, int index) {
        List<Works> worksList=new LinkedList<>();
        Connection connection=dataHelper.getConnection();
        Statement statement=dataHelper.getStatement(connection);

        int start=(index-1)*18;
        int end=index*18;
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM `works` WHERE author='"+name+"' ORDER BY id  DESC LIMIT "+start+","+end+";");
            while (resultSet.next()){
                long id=resultSet.getLong(1);
                String title=resultSet.getString(2);
                String description=resultSet.getString(3);
                String fenlei=resultSet.getString(4);
                String author=resultSet.getString(5);
                String time=resultSet.getString(6);
                long num_zan=resultSet.getLong(7);

                Works works=new Works(id,title,description,fenlei,author,time,num_zan);
                worksList.add(works);
            }
            dataHelper.close(resultSet,statement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worksList;
    }
}
