package com.navi.stock.service;

import java.util.List;

import com.navi.stock.entity.Transaction;

public interface TransactionService {

  List<Transaction> applyTransaction(String corporationName);

}
