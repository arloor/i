package restController;

import domain.Works;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.WorksService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/works")
public class WorksRestController {

    @Autowired
    WorksService worksService;

    @RequestMapping(value = "/atAuthor")
    public List<Works> getWorks(@RequestParam(defaultValue = "null") String name,@RequestParam(defaultValue = "1") int index){
        return worksService.getWorks(name,index);
    }
}
