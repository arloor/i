package crawler;

import Processor.Parser;
import org.apache.http.HttpEntity;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moontell on 2017/6/6.
 * 这个类继承自Parser，也是自己项目里定义的类，是对Jsoup的一个简单封装
 * 这个类的作用是获取新浪财经首页目标div中所有文章的连接
 */
public class TitleGetterParser extends Parser {
    public TitleGetterParser(HttpEntity entity) {
        super(entity);
    }

    public List<String> getTitles() {
        ArrayList<String> titleList = new ArrayList<>();

            Element newsDiv = doc.getElementById("blk_hdline_01");
            Elements as = newsDiv.getElementsByTag("a");
            String title = new String();

        boolean exsits = false;
            for (Element cell : as
                    ) {
                exsits = false;
                title = cell.attr("href");

                //去除重复的链接
                for (String oldTitle : titleList
                        ) {
                    if (oldTitle.equals(title))
                        exsits = true;
                    break;
                }
                if (!exsits)
                    titleList.add(title);
            }
        return titleList;
    }
}
