package dao.impl;

import dao.PocoDao;
import datahelper.DataHelper;
import domain.PocoImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by moontell on 2017/6/14.
 */
@Service
public class PocoDaoImpl implements PocoDao{
    @Autowired
    DataHelper dataHelper;

    @Override
    public List<PocoImage> getPhotos() {
        List<PocoImage> pocoImages=new ArrayList<>();
        Connection conn=dataHelper.getConnection();
        Statement statement=dataHelper.getStatement(conn);
        String sql="select * from `poco_img`;";
        try {
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                String link=resultSet.getString(2);
                String imgSrc=resultSet.getString(3);
                PocoImage pocoImage=new PocoImage();
                pocoImage.setLink(link);
                pocoImage.setImgSrc(imgSrc);
                pocoImages.add(pocoImage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pocoImages;
    }
}
