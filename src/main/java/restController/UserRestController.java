package restController;

import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    ResponseEntity<UserInfo> getUserInfo(@RequestParam(defaultValue = "null") String name){
        UserInfo userInfo=userService.getUserInfo(name);
        return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
    }
}
