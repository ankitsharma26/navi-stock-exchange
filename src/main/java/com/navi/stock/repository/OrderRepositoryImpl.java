package com.navi.stock.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.navi.stock.constant.OrderType;
import com.navi.stock.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

  /**
   * This is to Fetch Priority Queue for SELL/BUY for any corporation.
   *
   * @param corporationName - Corporation Name which Stock belongs to
   * @param orderType - SELL/BUY
   *
   * @return - PriorityQueue<Order>
   */
  @Override
  public PriorityQueue<Order> findByCorporationAndOrderType(String corporationName, OrderType orderType) {
    if (corporationBuySellOrderMapSorted.containsKey(corporationName)) {

      return corporationBuySellOrderMapSorted.get(corporationName).get(orderType);
    }

    return null;
  }

  /**
   * Call to save new Order
   *
   * @param order - Order to be saved
   *
   * @return - Order saved
   */
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

  /**
   * Call to delete the order
   *
   * @param order - Order deleted
   */
  @Override
  public void delete(Order order) {
    orders.remove(order);
  }

}
