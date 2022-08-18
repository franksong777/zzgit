package com.eagle;

import com.eagle.order.dao.OrderDao;
import com.eagle.order.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private OrderDao orderDao;

	/*@Test
	void getById() {
		Order order = orderDao.selectById(101);
		System.out.println(order);
	}*/

	/*@Test
	void getAll(){
		List<Order> orders = orderDao.selectList(null);
		System.out.println(orders);
	}*/

	@Test
	void getOrderById(){
		Order orderById = orderDao.getOrderById(101L);
		System.out.println(orderById);
	}

	@Test
	void getOrderAll(){
		List<Order> orderAll = orderDao.getOrderAll();
		System.out.println(orderAll);
	}



}

