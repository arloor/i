package service.impl;

import dao.UserDao;
import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public String login(String name, String password) {
        return userDao.login(name,password);
    }

    @Override
    public UserInfo getUserInfo(String name) {
        return userDao.getUserInfo(name);
    }
}
