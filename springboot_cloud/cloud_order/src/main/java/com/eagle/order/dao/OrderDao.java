package com.eagle.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eagle.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface OrderDao extends BaseMapper<Order> {
    @Select("select id,user_id,name,price,num from tb_order where id = #{id}" )
    Order getOrderById(Long id);

    @Select("select id,user_id,name,price,num from tb_order" )
    List<Order> getOrderAll();
}

