package com.navi.stock.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.navi.stock.constant.OrderType;
import com.navi.stock.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

  @Override
  public PriorityQueue<Order> findByCorporationAndOrderType(String corporationName, OrderType orderType) {
    if (corporationBuySellOrderMapSorted.containsKey(corporationName)) {

      return corporationBuySellOrderMapSorted.get(corporationName).get(orderType);
    }

    return null;
  }

  @Override
  public Order save(Order order) {
    order.validate();
    if (!corporationBuySellOrderMapSorted.containsKey(order.getCorporationName())) {
      Map<OrderType, PriorityQueue<Order>> orderMap = new HashMap<>();
      orderMap.put(OrderType.SELL, new PriorityQueue<>());
      orderMap.put(OrderType.BUY, new PriorityQueue<>());
      corporationBuySellOrderMapSorted.put(order.getCorporationName(), orderMap);
    }

    corporationBuySellOrderMapSorted.get(order.getCorporationName()).get(order.getOrderType()).offer(order);

    orders.add(order);
    return order;
  }

  @Override
  public void delete(Order order) {
    orders.remove(order);
  }

}
