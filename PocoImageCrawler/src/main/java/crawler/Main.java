package crawler;

import java.io.IOException;

/**
 * Created by moontell on 2017/6/16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        PocoCrawler crawler=new PocoCrawler();
        String[] urls={
                //其他
                //"http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-12.html#list",
                //纪实
                //"http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-3.html#list",
                //LOMO
                //"http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-5.html#list",
                //手机snap
                //"http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-7.html#list",
                //达物
                //"http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-8.html#list",
                //宠物
                //"http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-9.html#list",
                //美食
                //"http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-10.html#list",

                //生态
                "http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-2.html#list",
                //观念
                "http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-6.html#list",
                //生活
                "http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-4.html#list",
                //人像
                "http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-0.html#list",
                //性感
                "http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-11.html#list",
                //所有
                "http://photo.poco.cn/like/index-upi-tpl_type-hot.html#list",
                //风景
                "http://photo.poco.cn/like/index-upi-tpl_type-hot-channel_id-1.html#list",

        };
        for (String url:urls
             ) {
            crawler.crawler(url);
        }

    }
}
