package com.navi.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.List;
import java.util.PriorityQueue;

import com.navi.stock.constant.OrderType;
import com.navi.stock.entity.Order;
import com.navi.stock.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

  @Mock
  private OrderServiceImpl orderService;

  @Test
  void applyFullOrderQuantityTransaction() {
    TransactionServiceImpl transactionService = new TransactionServiceImpl(orderService);

    Order orderBuy = new Order();
    orderBuy.setId(1L);
    orderBuy.setCorporationName("BAC");
    orderBuy.setQuantityRequested(100L);
    orderBuy.setRequestedPrice(240.12);
    orderBuy.setOrderType(OrderType.BUY);
    orderBuy.setRequestedAt(LocalTime.parse("09:45"));

    PriorityQueue<Order> pqBuy = new PriorityQueue<>();
    pqBuy.offer(orderBuy);
    Mockito.when(orderService.getPriorityOrderByCorporationAndType("BAC", OrderType.BUY)).thenReturn(pqBuy);

    Order orderSell = new Order();
    orderSell.setId(2L);
    orderSell.setCorporationName("BAC");
    orderSell.setQuantityRequested(100L);
    orderSell.setRequestedPrice(230.12);
    orderSell.setOrderType(OrderType.SELL);
    orderSell.setRequestedAt(LocalTime.parse("09:46"));

    PriorityQueue<Order> pqSell = new PriorityQueue<>();
    pqSell.offer(orderSell);
    Mockito.when(orderService.getPriorityOrderByCorporationAndType("BAC", OrderType.SELL)).thenReturn(pqSell);

    List<Transaction> transactions = transactionService.applyTransaction("BAC");

    assertEquals(transactions.get(0).getQuantity(), orderBuy.getQuantityRequested());
  }

  @Test
  void applyPartialBuyOrderQuantityTransaction() {
    TransactionServiceImpl transactionService = new TransactionServiceImpl(orderService);

    Order orderBuy = new Order();
    orderBuy.setId(1L);
    orderBuy.setCorporationName("BAC");
    orderBuy.setQuantityRequested(110L);
    orderBuy.setRequestedPrice(240.12);
    orderBuy.setOrderType(OrderType.BUY);
    orderBuy.setRequestedAt(LocalTime.parse("09:45"));

    PriorityQueue<Order> pqBuy = new PriorityQueue<>();
    pqBuy.offer(orderBuy);
    Mockito.when(orderService.getPriorityOrderByCorporationAndType("BAC", OrderType.BUY)).thenReturn(pqBuy);

    Order orderSell = new Order();
    orderSell.setId(2L);
    orderSell.setCorporationName("BAC");
    orderSell.setQuantityRequested(100L);
    orderSell.setRequestedPrice(230.12);
    orderSell.setOrderType(OrderType.SELL);
    orderSell.setRequestedAt(LocalTime.parse("09:46"));

    PriorityQueue<Order> pqSell = new PriorityQueue<>();
    pqSell.offer(orderSell);
    Mockito.when(orderService.getPriorityOrderByCorporationAndType("BAC", OrderType.SELL)).thenReturn(pqSell);

    List<Transaction> transactions = transactionService.applyTransaction("BAC");

    assertEquals(transactions.get(0).getQuantity(), orderSell.getQuantityRequested());
  }

  @Test
  void applyPartialSellOrderQuantityTransaction() {
    TransactionServiceImpl transactionService = new TransactionServiceImpl(orderService);

    Order orderBuy = new Order();
    orderBuy.setId(1L);
    orderBuy.setCorporationName("BAC");
    orderBuy.setQuantityRequested(100L);
    orderBuy.setRequestedPrice(240.12);
    orderBuy.setOrderType(OrderType.BUY);
    orderBuy.setRequestedAt(LocalTime.parse("09:45"));

    PriorityQueue<Order> pqBuy = new PriorityQueue<>();
    pqBuy.offer(orderBuy);
    Mockito.when(orderService.getPriorityOrderByCorporationAndType("BAC", OrderType.BUY)).thenReturn(pqBuy);

    Order orderSell = new Order();
    orderSell.setId(2L);
    orderSell.setCorporationName("BAC");
    orderSell.setQuantityRequested(110L);
    orderSell.setRequestedPrice(230.12);
    orderSell.setOrderType(OrderType.SELL);
    orderSell.setRequestedAt(LocalTime.parse("09:46"));

    PriorityQueue<Order> pqSell = new PriorityQueue<>();
    pqSell.offer(orderSell);
    Mockito.when(orderService.getPriorityOrderByCorporationAndType("BAC", OrderType.SELL)).thenReturn(pqSell);

    List<Transaction> transactions = transactionService.applyTransaction("BAC");

    assertEquals(transactions.get(0).getQuantity(), orderBuy.getQuantityRequested());
  }
}