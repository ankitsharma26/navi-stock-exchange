package com.navi.stock.service;

import java.util.PriorityQueue;

import com.navi.stock.constant.OrderType;
import com.navi.stock.entity.Order;

public interface OrderService {

  /**
   * Used to save Order from the Input Line
   *
   * @param orderLine - Input Line
   *
   * @return Saved Order
   */
  Order addOrder(String orderLine);

  /**
   * Used to delete Order from Persistence System
   *
   * @param order - Order to be deleted
   */
  void removeOrder(Order order);

  /**
   * Used to fetch PriorityQueue for the BUY/SELL Order for given Corporation
   *
   * @param corporationName - Corporation Name stock belongs to
   * @param orderType - Order Type BUY/SELL
   *
   * @return - PriorityQueue<Order>
   */
  PriorityQueue<Order> getPriorityOrderByCorporationAndType(String corporationName, OrderType orderType);

}
