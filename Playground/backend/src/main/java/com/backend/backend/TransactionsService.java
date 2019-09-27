package com.backend.backend;
import com.backend.backend.Statistics;
import com.backend.backend.Transactions;

public interface TransactionsService {

    public Statistics getStats();
    public void saveTransaction(Transactions transaction);
    public void deleteTransaction();
}