package com.eagle;

import com.eagle.user.dao.UserDao;
import com.eagle.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	void getById() {
		User user = userDao.selectById(1);
		System.out.println(user);
	}

	@Test
	void getAll(){
		List<User> users = userDao.selectList(null);
		System.out.println(users);
	}

}
