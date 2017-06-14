package crawler;

import Processor.Parser;
import org.apache.http.HttpEntity;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moontell on 2017/6/14.
 */
public class AlinkParser extends Parser{
    public AlinkParser(HttpEntity entity)  {
        super(entity);
    }




    public List<String> getAlinks(){
        List<String> alinks=new ArrayList<>();
        //String path = "pocoImg\\links.txt";
        try {
//            FileWriter fw=new FileWriter(path,true);
//            BufferedWriter bw=new BufferedWriter(fw);

            Elements imgBox=doc.getElementsByClass("img-box");
            for (Element cell:imgBox
                    ) {
                String linkHerf=cell.getElementsByTag("a").first().attr("href");
                alinks.add(linkHerf);
//                bw.write(linkHerf+"\n");
            }

//            bw.flush();
//            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alinks;
    }
}
