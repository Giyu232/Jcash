package org.example.service;

import org.example.model.TransactionMdl;
import org.example.util.Database;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Transaction {

    private List<TransactionMdl> transactionMdls;
    private Database db;

    public Transaction(Database db) throws SQLException {
        this.db = db;
    }

    public List<TransactionMdl> getTransactions() {
        return transactionMdls;
    }

    public void getTransactionsByUserID(int userId) throws SQLException {
        this.transactionMdls = this.db.getTransactionsByUserID(userId);
    }

    public void addTransaction(String type, double amount, String details, Date date, int userId) throws SQLException {
        this.transactionMdls.add(this.db.addTransactionByUserId(type, amount, details, date, userId));
    }

    public boolean addTransactionReceiverLogs(String type, double amount, String details, Date date, int userId) throws SQLException {
        TransactionMdl t = this.db.addTransactionByUserId(type, amount, details, date, userId);
        return t != null;
    }


}
