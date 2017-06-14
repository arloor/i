package crawler;

import Processor.Parser;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by moontell on 2017/6/14.
 */
public class ImgUrlParser extends Parser{
    public ImgUrlParser(HttpEntity entity) {
        super(entity);
    }

    public void setDoc(HttpEntity response){
        try {
            this.doc= Jsoup.parse(EntityUtils.toString(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getImgUrl() {
//        String path = "pocoImg/imgUrls.txt";
        String imgUrl=new String();
        try {
//            FileWriter fw=new FileWriter(path,true);
//            BufferedWriter bw=new BufferedWriter(fw);


            Elements scripts=doc.getElementsByTag("script");
            String imgScript=null;
            //System.out.println(scripts.size());
            for (Element cell:scripts
                    ) {
                if(cell.html().contains("full_item_img.src")) {
                    imgScript = cell.html();
                    //bw.write(imgScript+"\n");
                    break;
                }
            }
            if(imgScript!=null){
                //todo：获取链接
                int start=imgScript.indexOf("full_item_img.src\t = '")+
                        "full_item_img.src\t = '".length();
                imgScript=imgScript.substring(start);
                imgScript=imgScript.substring(0,imgScript.indexOf("'"));
//                bw.write(imgScript+"\n");
                imgUrl=imgScript;

            }

//            bw.flush();
//            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return imgUrl;
    }
}
