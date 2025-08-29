package dao;

import model.User;

public interface UserDao {
    User findByUserName(String username);
    User login(String username, String password);
    void insert(User user);
    User findByEmail(String email);
    User findByPhone(String phone);

}
