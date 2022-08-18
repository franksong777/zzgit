package com.eagle.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eagle.order.client.FeignUserClient;
import com.eagle.order.dao.OrderDao;
import com.eagle.order.domain.Order;
import com.eagle.order.service.OrderService;
import com.eagle.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {
    @Autowired
    private OrderDao orderDao;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private FeignUserClient feignUserClient;
    @Override
    public Order getBy(Long id) {
        Order order = orderDao.getOrderById(id);
        User user = feignUserClient.getById(order.getUserId());
        order.setUser(user);
        return order;
    }

   /* @Override
    public Order getBy(Long id) {
        Order order = orderDao.getOrderById(id);
      //  String url = "http://localhost:8081/users/"+order.getUserId();    // not need when use eureka / nacos
        String url = "http://userservice/users/"+order.getUserId();            // eureka / nacos
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);

        return order;
    }*/
}
