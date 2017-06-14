package dao.impl;

import dao.NewsDao;
import datahelper.DataHelper;
import domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu Qin
 * @since 2017/6/10
 */
@Service
public class NewsDaoImpl implements NewsDao {
    @Autowired
    private DataHelper dao;

    @Override
    public List<News> getNews() {
        Connection connection = dao.getConnection();
        Statement statement = dao.getStatement(connection);
        List<News> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM stock_news");
            while (resultSet.next()) {
                News news = new News();
                news.setId(resultSet.getInt(1));
                news.setTitle(resultSet.getString(2));
                news.setTime(resultSet.getString(3));
                news.setArticle(resultSet.getString(4));
                list.add(news);
            }
            dao.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}