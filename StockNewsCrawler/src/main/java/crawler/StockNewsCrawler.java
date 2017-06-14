package crawler;

import Downloader.Graber;
import Downloader.MyHttpClient;
import Downloader.RequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.entity.BufferedHttpEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by moontell on 2017/6/6.
 * 用于爬取新浪财经上的新闻
 * 使用了自己之前对httpclient的进行封装的一个依赖，用maven导入的依赖
 * 地址：https://github.com/arloor/EasyCrawler
 */
public class StockNewsCrawler {
    public static void main(String[] args) throws IOException {
        //创建httpclient的封装类，并且创建爬虫主类
        MyHttpClient myHttpClient = new MyHttpClient(2000, 5);
        Graber graber = new Graber(myHttpClient);


        //爬取新浪财经的首页，获取文章链接链表
        RequestEntity requestEntity = new RequestEntity(URI.create("http://finance.sina.com.cn/"));
        graber.add(requestEntity);
        HttpEntity response = graber.grab();
        response = new BufferedHttpEntity(response);

        TitleGetterParser titleGetterParser = new TitleGetterParser(response);
        List<String> links = titleGetterParser.getTitles();


        //新建文件夹保存爬取的网页
        File file = new File("news");
        if (!file.exists()) {
            file.mkdir();
        }

        //对各个文章链接进行解析：保存，解析获得需要的部分
        List<String[]> contentList = new ArrayList<>();
        int i = 0;
        for (String href : links
                ) {
            //爬取单独的一个链接的文章
            requestEntity.setUri(URI.create(href));
            response = graber.grab();
            response = new BufferedHttpEntity(response);

            //把文章写到数据库，方便之后用Jsoup解析。
            //（不用解析文件的方式发现会出现乱码，所以先保存文件）
            String path = "news/" + i + ".html";
            File news = new File(path);
            FileOutputStream fo = new FileOutputStream(path);
            response.writeTo(fo);
            fo.close();


            Document document = Jsoup.parse(news, "UTF-8");
            //文章标题
            Element title = document.getElementById("artibodyTitle");
            //时间和来源
            Element time = document.getElementsByClass("time-source").first();
            //正文
            Element artibody = document.getElementById("artibody");

            //如果这个连接中没有目标Element，则跳过这一链接
            if (time == null || title == null || artibody == null)
                continue;
            Elements elements;
            //删除div中对新浪app的广告
            elements = artibody.getElementsByClass("finance_app_zqtg");
            for (Element cell : elements
                    ) {
                cell.remove();
            }
            //删除div中的script
            elements = artibody.getElementsByTag("script");
            for (Element cell : elements
                    ) {
                cell.remove();
            }
            String titleString = title.text();
            String timeString = time.text();
            String article = artibody.html();

            //删除正文以外的东西
            if (article.indexOf("<!--正文部分end-->") != -1)
                article = article.substring(0, article.indexOf("<!--正文部分end-->"));
            if (article.indexOf("<!--股吧推广-->") != -1)
                article = article.substring(0, article.indexOf("<!--股吧推广-->"));



            String[] contents = new String[3];
            contents[0] = titleString;
            contents[1] = timeString;
            contents[2] = article;

            contentList.add(contents);



            //下面是写到文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(news));
            bw.write(titleString + "\n"
                    + timeString + " "
                    //+media_name.text()
                    + "\n"
                    + article
            );
            bw.close();

            i++;
        }

        //关闭连接等
        graber.stop();
        //下面是写到数据库
        DataHelper dataHelper = new DataHelper();
        dataHelper.save(contentList);
    }
}
