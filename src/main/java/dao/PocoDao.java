package dao;

import domain.PocoImage;

import java.util.List;

/**
 * Created by moontell on 2017/6/14.
 */
public interface PocoDao {

     List<PocoImage> getPhotos(int num);
}
