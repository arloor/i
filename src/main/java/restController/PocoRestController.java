package restController;

import domain.PocoImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.PocoService;

import java.util.List;

/**
 * Created by moontell on 2017/6/14.
 */
@RestController
@RequestMapping(value = "/api/poco_img")
public class PocoRestController {
    @Autowired
    private PocoService pocoService;

    /*用于执行shell
    String shpath="/home/felven/word2vec/demo-classes.sh";
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));

    */

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<PocoImage>> getNews() {
        List<PocoImage> pocoImages = pocoService.getPhotos();
        return pocoImages.size() > 0 ? new ResponseEntity<>(pocoImages, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
