package com.navi.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.PriorityQueue;

import com.navi.stock.constant.OrderType;
import com.navi.stock.entity.Order;
import com.navi.stock.exception.BadAttributeException;
import com.navi.stock.repository.OrderRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

  @Mock
  private OrderRepositoryImpl orderRepository;

  @Test
  @DisplayName("Sell Order Test")
  void addSellOrder() {
    OrderServiceImpl orderService = new OrderServiceImpl(orderRepository);

    Order order = new Order();
    order.setId(1L);
    order.setCorporationName("BAC");
    order.setQuantityRequested(100L);
    order.setRequestedPrice(240.12);
    order.setOrderType(OrderType.SELL);
    order.setRequestedAt(LocalTime.parse("09:45"));

    Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

    String line = "#1 09:45 BAC sell 240.12 100";
    Order order2 = orderService.addOrder(line);

    assertEquals(order, order2);
  }

  @Test
  @DisplayName("Buy Order Test")
  void addBuyOrder() {
    OrderServiceImpl orderService = new OrderServiceImpl(orderRepository);

    Order order = new Order();
    order.setId(1L);
    order.setCorporationName("BAC");
    order.setQuantityRequested(100L);
    order.setRequestedPrice(240.12);
    order.setOrderType(OrderType.BUY);
    order.setRequestedAt(LocalTime.parse("09:45"));

    Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

    String line = "#1 09:45 BAC buy 240.12 100";
    Order order2 = orderService.addOrder(line);

    assertEquals(order, order2);
  }

  @Test
  @DisplayName("Remove Order")
  void removeOrder() {
    OrderServiceImpl orderService = new OrderServiceImpl(orderRepository);

    Order order = new Order();
    order.setId(1L);
    order.setCorporationName("BAC");
    order.setQuantityRequested(100L);
    order.setRequestedPrice(240.12);
    order.setOrderType(OrderType.BUY);
    order.setRequestedAt(LocalTime.parse("09:45"));

    orderService.removeOrder(order);

    Mockito.verify(orderRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Order.class));
  }

  @Test
  @DisplayName("Invalid Input Test")
  void invalidOrder() {
    OrderServiceImpl orderService = new OrderServiceImpl(orderRepository);

    String line = "#1 09:45 BAC sell 240.12";

    assertThrows(BadAttributeException.class, () -> {
      orderService.addOrder(line);
    });
  }

  @Test
  @DisplayName("Fetch Priority Queue Test")
  void fetchPriorityQueue() {
    OrderServiceImpl orderService = new OrderServiceImpl(orderRepository);

    Order order = new Order();
    order.setId(1L);
    order.setCorporationName("BAC");
    order.setQuantityRequested(100L);
    order.setRequestedPrice(240.12);
    order.setOrderType(OrderType.BUY);
    order.setRequestedAt(LocalTime.parse("09:45"));

    PriorityQueue<Order> pq = new PriorityQueue<>();
    pq.offer(order);
    Mockito.when(orderRepository.findByCorporationAndOrderType("BAC", OrderType.BUY)).thenReturn(pq);

    PriorityQueue<Order> pq2 = orderService.getPriorityOrderByCorporationAndType("BAC", OrderType.BUY);

    assertEquals(pq, pq2);
  }
}