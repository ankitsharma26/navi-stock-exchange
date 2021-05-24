package com.navi.stock.entity;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.navi.stock.constant.OrderType;
import com.navi.stock.exception.BadAttributeException;
import lombok.Data;

@Data
public class Order implements Comparable<Order> {

  private Long id;

  private LocalTime requestedAt;

  private String corporationName;

  private OrderType orderType;

  private Double requestedPrice;

  private Long quantityRequested;

  public void validate() {
    if (id == null
        || requestedAt == null
        || corporationName == null
        || orderType == null
        || requestedPrice == null
        || quantityRequested == null) {
      throw new BadAttributeException("Insufficient Data");
    }
  }

  @Override
  public int compareTo(Order o) {
    double priceDiff = this.requestedPrice - o.requestedPrice;
    if (o.orderType == OrderType.SELL) {
      if (priceDiff < 0) {

        return -1;
      } else if (priceDiff > 0) {

        return 1;
      }
    }
    if (o.orderType == OrderType.BUY) {
      if (priceDiff < 0) {

        return 1;
      } else if (priceDiff > 0) {

        return -1;
      }
    }

    if (this.requestedAt.until(o.requestedAt, ChronoUnit.MINUTES) > 0) {
      return -1;
    } else {
      return 1;
    }
  }
}
