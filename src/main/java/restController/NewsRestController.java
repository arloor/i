package restController;

import domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.NewsService;

import java.util.List;

/**
 * Created by moontell on 2017/6/7.
 */
@RestController
@RequestMapping(value = "/api/stock_news")
public class NewsRestController {

    @Autowired
    private NewsService newsService;

    /*用于执行shell
    String shpath="/home/felven/word2vec/demo-classes.sh";
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));

    */

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<News>> getNews() {
        List<News> newsList = newsService.getNews();
        return newsList.size() > 0 ? new ResponseEntity<>(newsList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
