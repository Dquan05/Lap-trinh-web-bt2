package dao;

import model.User;

public interface UserDao {
    User findByUserName(String username);
    User login(String username, String password);
}
