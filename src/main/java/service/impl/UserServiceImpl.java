package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {
	private final UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = this.findByUserName(username);
		if (user != null && password.equals(user.getPassWord())) { // dùng getPassWord()
			return user;
		}
		return null;
	}

	@Override
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public boolean register(String email, String password, String username, String fullName, String phone) {
		if (checkExistEmail(email) || checkExistUsername(username) || checkExistPhone(phone)) {
			return false;
		}

		User user = new User(0, email, username, fullName, password, null, 2, phone, new java.util.Date());

		insert(user);
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.findByEmail(email) != null;
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.findByUserName(username) != null;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.findByPhone(phone) != null;
	}
}
