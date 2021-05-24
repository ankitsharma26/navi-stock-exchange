package com.navi.stock.entity;

import java.time.LocalTime;

import lombok.Data;

@Data
public class Transaction {

  private Order buyerOrder;

  private Order sellerOrder;

  private LocalTime completedAt;

  private Long quantity;

}
