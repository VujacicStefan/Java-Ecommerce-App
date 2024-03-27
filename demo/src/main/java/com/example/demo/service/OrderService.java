package com.example.demo.service;

import com.example.demo.Entity.Orders;
import com.example.demo.dao.OrderDao;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class OrderService {
    OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

   public Optional<Orders> getOrder(long id) {
         if (orderDao.findById(id).isPresent()) {
             return orderDao.findById(id);
        }
        return null;
    }

    public Orders postOrder(Orders order) {
        return orderDao.save(order);
    }


    public boolean deleteOrder(long id) {
        if (orderDao.findById(id).isPresent()) {
            orderDao.deleteById(id);
           System.out.println("Deleted");
        }
        return true;
    }


}