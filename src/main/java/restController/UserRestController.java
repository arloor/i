package restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    String login(@RequestParam(defaultValue = "") String name,@RequestParam(defaultValue = "") String password){
        return userService.login(name,password);
    }

}
