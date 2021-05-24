package com.navi.stock.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.navi.stock.constant.OrderType;
import com.navi.stock.entity.Order;

/**
 * Order Repository is used to Persist the Stream of Inputs in Static Variables
 * We can persist this in DB instead of Static.
 */
public interface OrderRepository {

  /**
   * orders - Stores all the orders List
   */
  List<Order> orders = new ArrayList<>();

  /**
   * corporationBuySellOrderMapSorted - Stores all the Buy Sell Orders in PriorityQueue in a Map for Corporation
   */
  Map<String, Map<OrderType, PriorityQueue<Order>>> corporationBuySellOrderMapSorted = new HashMap<>();

  /**
   * This is to Fetch Priority Queue for SELL/BUY for any corporation.
   *
   * @param corporationName - Corporation Name which Stock belongs to
   * @param orderType - SELL/BUY
   *
   * @return - PriorityQueue<Order>
   */
  PriorityQueue<Order> findByCorporationAndOrderType(String corporationName, OrderType orderType);

  /**
   * Call to save new Order
   *
   * @param order - Order to be saved
   *
   * @return - Order saved
   */
  Order save(Order order);

  /**
   * Call to delete the order
   *
   * @param order - Order deleted
   */
  void delete(Order order);
}
