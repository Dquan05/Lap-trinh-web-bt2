package service;

import model.User;

public interface UserService {
    User login(String username, String password);

    User findByUserName(String username);

    void insert(User user);

    boolean register(String email, String password, String username, String fullName, String phone);

    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
}
