package dao;

import domain.News;

import java.util.List;

/**
 * @author Liu Qin
 * @since 2017/6/10
 */
public interface NewsDao {

    /**
     * 获取新闻
     *
     * @return
     */
    List<News> getNews();

}
