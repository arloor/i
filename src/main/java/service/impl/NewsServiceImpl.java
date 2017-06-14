package service.impl;

import dao.NewsDao;
import domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.NewsService;

import java.util.List;

/**
 * @author Liu Qin
 * @since 2017/6/10
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public List<News> getNews() {
        return newsDao.getNews();
    }

}
