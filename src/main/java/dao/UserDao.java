package dao;

import domain.UserInfo;

public interface UserDao {
    String login(String name,String password);

    UserInfo getUserInfo(String name);
}
