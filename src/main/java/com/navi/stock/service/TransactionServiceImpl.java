package com.navi.stock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.navi.stock.constant.OrderType;
import com.navi.stock.entity.Order;
import com.navi.stock.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionServiceImpl implements TransactionService {

  private final OrderService orderService;

  @Override
  public List<Transaction> applyTransaction(String corporationName) {
    List<Transaction> transactions = new ArrayList<>();

    PriorityQueue<Order> buyPriorityQueue = orderService.getPriorityOrderByCorporationAndType(corporationName, OrderType.BUY);
    PriorityQueue<Order> sellPriorityQueue = orderService.getPriorityOrderByCorporationAndType(corporationName, OrderType.SELL);

    while (!sellPriorityQueue.isEmpty()
        && !buyPriorityQueue.isEmpty()
        && sellPriorityQueue.peek().getRequestedPrice() < buyPriorityQueue.peek().getRequestedPrice()) {
      Order sellOrder = sellPriorityQueue.poll();
      Order buyOrder = buyPriorityQueue.poll();

      Transaction transaction = new Transaction();
      transaction.setBuyerOrder(buyOrder);
      transaction.setSellerOrder(sellOrder);

      if (sellOrder.getQuantityRequested() > buyOrder.getQuantityRequested()) {
        transaction.setQuantity(buyOrder.getQuantityRequested());
        sellOrder.setQuantityRequested(sellOrder.getQuantityRequested() - buyOrder.getQuantityRequested());
        sellPriorityQueue.offer(sellOrder);
        orderService.removeOrder(buyOrder);
      } else if (buyOrder.getQuantityRequested() > sellOrder.getQuantityRequested()) {
        transaction.setQuantity(sellOrder.getQuantityRequested());
        buyOrder.setQuantityRequested(buyOrder.getQuantityRequested() - sellOrder.getQuantityRequested());
        buyPriorityQueue.offer(buyOrder);
        orderService.removeOrder(sellOrder);
      } else {
        orderService.removeOrder(buyOrder);
        orderService.removeOrder(sellOrder);
        transaction.setQuantity(sellOrder.getQuantityRequested());
      }

      transactions.add(transaction);
    }

    return transactions;
  }
}
