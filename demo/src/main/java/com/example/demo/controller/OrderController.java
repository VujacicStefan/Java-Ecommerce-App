package com.example.demo.controller;

import com.example.demo.Entity.Orders;
import com.example.demo.service.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {

    OrderService orderService;
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("post/order")
    ResponseEntity<Orders>post_order(@RequestBody Orders order) {
        orderService.postOrder(order);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("header-name", "header-value");
        return ResponseEntity.ok().headers(httpHeaders).body(order);

    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("get_order/{id}")
    Optional<Orders> order (@PathVariable long id) {
        return orderService.getOrder(id);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("deleteOrder/{id}")
    boolean deleteOrder(@PathVariable long id) {
        return orderService.deleteOrder(id);

    }





}
