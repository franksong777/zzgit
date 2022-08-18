package com.eagle.order.client;

import com.eagle.user.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userservice")
public interface FeignUserClient {
    @GetMapping("/users/{id}")
    User getById(@PathVariable Long id);
}
