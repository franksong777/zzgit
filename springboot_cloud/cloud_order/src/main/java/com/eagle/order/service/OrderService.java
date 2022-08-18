package com.eagle.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eagle.order.domain.Order;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

public interface OrderService extends IService<Order> {
    @Select("select id,user_id,name,price,num from tb_order where id = #{id}")
    Order getBy(Long id);
}
