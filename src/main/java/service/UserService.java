package service;

import domain.UserInfo;

public interface UserService {
    String login(String name,String password);
    UserInfo getUserInfo(String name);
}
