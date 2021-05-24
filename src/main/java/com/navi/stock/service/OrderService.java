package com.navi.stock.service;

import java.util.PriorityQueue;

import com.navi.stock.constant.OrderType;
import com.navi.stock.entity.Order;

public interface OrderService {

  Order addOrder(String orderLine);

  void removeOrder(Order order);

  PriorityQueue<Order> getPriorityOrderByCorporationAndType(String corporationName, OrderType orderType);

}
