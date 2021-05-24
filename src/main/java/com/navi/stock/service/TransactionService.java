package com.navi.stock.service;

import java.util.List;

import com.navi.stock.entity.Transaction;

public interface TransactionService {

  /**
   * Algorithm to Apply Transaction for a corporation Name
   *
   * @param corporationName - Corporation Name stock belongs to
   *
   * @return - List of Transaction at present Corporation State
   */
  List<Transaction> applyTransaction(String corporationName);

}
