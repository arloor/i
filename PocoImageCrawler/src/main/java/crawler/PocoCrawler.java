package crawler;

import Downloader.Graber;
import Downloader.MyHttpClient;
import Downloader.RequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.entity.BufferedHttpEntity;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by moontell on 2017/6/6.
 * 用于爬取新浪财经上的新闻
 * 使用了自己之前对httpclient的进行封装的一个依赖，用maven导入的依赖
 * 地址：https://github.com/arloor/EasyCrawler
 */
public class PocoCrawler {
    public void crawler(String url) throws IOException {
        //创建httpclient的封装类，并且创建爬虫主类
        MyHttpClient myHttpClient = new MyHttpClient(2000, 5);
        Graber graber = new Graber(myHttpClient);


        //获取文章链接链表
        RequestEntity requestEntity = new RequestEntity(URI.create(url));
        graber.add(requestEntity);
        HttpEntity response = graber.grab();
        response = new BufferedHttpEntity(response);


        AlinkParser alinkParser=new AlinkParser(response);
        List<String> links=alinkParser.getAlinks();


        //新建文件夹保存爬取的网页
        File file = new File("pocoImg");
        if (!file.exists()) {
            file.mkdir();
        }

//        String path = "pocoImg/index.html";
//        FileOutputStream fo = new FileOutputStream(path);
//        response.writeTo(fo);
//        fo.close();

        //----------------对单个网页处理，获取图片

        //todo
        //对各个文章链接进行解析：保存，解析获得需要的部分
        Map<String,String > linkAndImgSrcMap=new HashMap<>();
        ImgUrlParser imgUrlParser=new ImgUrlParser(response);
        for (String href : links
                ) {
            //爬取单独的一个链接的文章
            requestEntity.setUri(URI.create(href));
            response = graber.grab();
            response = new BufferedHttpEntity(response);
            imgUrlParser.setDoc(response);

            String imgUrl=imgUrlParser.getImgUrl();
            linkAndImgSrcMap.put(href,imgUrl);
        }
        graber.stop();

        //持久化
        DataHelper dao=new DataHelper();
        dao.save(linkAndImgSrcMap);
    }
}
