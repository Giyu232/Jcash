package org.example.service;

import org.example.model.User;
import org.example.util.Database;

import java.sql.SQLException;

public class Transfer {

    private UserService userService;
    private Transaction transaction;
    private Database db;

    public Transfer(UserService userService, Transaction transaction, Database db) {
        this.userService = userService;
        this.transaction = transaction;
        this.db = db;
    }
    public void updateSenderAndReceiverBalance(String mobileNumberReceiver, Double amount) throws SQLException {
        User receiverUser = this.userService.getUserByMobileNumber(mobileNumberReceiver);
        double receiverBalance = receiverUser.getBalance() + amount;
        int receiverUserId = receiverUser.getUserId();
        double senderBalance = this.userService.getCurrentUserBalance() - amount;
        int senderUserId = this.userService.getCurrentUserId();

        this.db.updateSenderAndTransferBalanceAndTransaction(senderUserId, receiverUserId, senderBalance, receiverBalance, amount, mobileNumberReceiver, this.userService.getCurrentUserMobileNumber());
        this.transaction.getTransactionsByUserID(senderUserId);
        this.userService.setCurrentUserBalance(senderBalance);
        System.out.println("Successfully sent to " + mobileNumberReceiver + "!");
    }
}
