package service;

import domain.PocoImage;

import java.util.List;

/**
 * Created by moontell on 2017/6/14.
 */
public interface PocoService {

    List<PocoImage> getPhotos(int num);
}
