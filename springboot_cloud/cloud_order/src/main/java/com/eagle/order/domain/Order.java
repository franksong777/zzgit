package com.eagle.order.domain;

import com.eagle.user.domain.User;
import lombok.Data;
import java.io.Serializable;

@Data
public class Order implements Serializable {
    private Long id;
    private String name;
    private Long price;
    private Integer num;
    private Long userId;
    private User user;
}
