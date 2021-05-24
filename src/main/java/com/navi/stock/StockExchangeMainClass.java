package com.navi.stock;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.navi.stock.entity.Order;
import com.navi.stock.entity.Transaction;
import com.navi.stock.exception.BadAttributeException;
import com.navi.stock.service.OrderService;
import com.navi.stock.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockExchangeMainClass implements CommandLineRunner {

  private final OrderService orderService;

  private final TransactionService transactionService;

  public static void main(String[] args) {
    SpringApplication.run(StockExchangeMainClass.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Scanner sc = new Scanner(new File(args[0]));
    List<Order> orders = new ArrayList<>();

    while (sc.hasNextLine()) {
      String orderLine = sc.nextLine();

      Order order;
      try {
        order = orderService.addOrder(orderLine);
      } catch (BadAttributeException badAttributeException) {
        System.err.println(badAttributeException.getMessage());

        return;
      }

      List<Transaction> transactions = transactionService.applyTransaction(order.getCorporationName());

      for (Transaction transaction : transactions) {
        System.out.println(
            "#" + transaction.getBuyerOrder().getId()
                + " " + transaction.getSellerOrder().getRequestedPrice()
                + " " + transaction.getQuantity()
                + " #" + transaction.getSellerOrder().getId()
        );
      }
    }

  }
}
