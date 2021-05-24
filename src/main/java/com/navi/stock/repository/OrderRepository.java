package com.navi.stock.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.navi.stock.constant.OrderType;
import com.navi.stock.entity.Order;

public interface OrderRepository {

  List<Order> orders = new ArrayList<>();

  Map<String, Map<OrderType, PriorityQueue<Order>>> corporationBuySellOrderMapSorted = new HashMap<>();

  PriorityQueue<Order> findByCorporationAndOrderType(String corporationName, OrderType orderType);

  Order save(Order order);

  void delete(Order order);
}
