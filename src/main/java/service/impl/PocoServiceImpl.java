package service.impl;

import dao.PocoDao;
import domain.PocoImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PocoService;

import java.util.List;

/**
 * Created by moontell on 2017/6/14.
 */
@Service
public class PocoServiceImpl implements PocoService{
    @Autowired
    PocoDao pocoDao;
    @Override
    public List<PocoImage> getPhotos( int num) {
        return pocoDao.getPhotos(num);
    }
}
