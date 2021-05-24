package com.navi.stock.entity;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.navi.stock.constant.OrderType;
import com.navi.stock.exception.BadAttributeException;
import lombok.Data;

/**
 * Order Entity which stores Both SELL/BUY Orders
 */
@Data
public class Order implements Comparable<Order> {

  // Order ID
  private Long id;

  // Request Time
  private LocalTime requestedAt;

  // Stock Corporation Name
  private String corporationName;

  // Order Type BUY/SELL
  private OrderType orderType;

  // Requested Price
  private Double requestedPrice;

  // Quantity Requested
  private Long quantityRequested;

  /**
   * Validation if any of the field is null
   */
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

  /**
   * Comparison is done based on SELL/BUY Order Type, If SELL then Minimum Price, If BUY then Maximum price else Based on Time
   *
   * @param o - Order Object compared to
   *
   * @return integer
   */
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
