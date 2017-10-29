package service.impl;

import dao.WorksDao;
import domain.Works;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.WorksService;

import java.util.List;

@Service
public class WorksServiceImpl implements WorksService {
    @Autowired
    WorksDao worksDao;

    @Override
    public List<Works> getWorks(String name, int index) {
        return worksDao.getWorks(name,index);
    }
}
