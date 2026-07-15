package org.example.main;


import org.example.controller.UserCtl;
import org.example.service.Transaction;
import org.example.service.Transfer;
import org.example.service.UserService;
import org.example.util.Auth;
import org.example.util.Database;

import java.sql.SQLException;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        startApp(db);
    }

    public static void startApp(Database db) throws SQLException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            Auth auth = new Auth();
            UserService userService = new UserService(db, auth);
            Transaction transaction = new Transaction(db);
            Transfer transfer = new Transfer(userService, transaction,db);
            UserCtl uc = new UserCtl(transaction, userService, transfer);

            boolean userStatus = setUpAccount(uc, sc);
            if(!userStatus){
                break;
            }

            boolean loggedIn = displayMenu(uc, sc);

            if (!loggedIn) {
                break;
            }
        }
        sc.close();
    }

    public static boolean setUpAccount(UserCtl uc, Scanner sc) {
        int choice;
        try{
            do{
                System.out.println("=======================================");
                System.out.println("=================LOGIN=================");
                System.out.println("=======================================");
                System.out.println("[1] Log-in");
                System.out.println("[2] Register");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        return uc.logInUser();
                    case 2:
                        uc.registerUser();
                        break;
                    default:
                        System.out.println("Thank you for using JCash Application");
                        return false;
                }
            }while(true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean displayMenu(UserCtl uc, Scanner sc) {
        int choice;
        System.out.println("===============>Dashboard<==============");
        System.out.println("");
        try{
            do{
                System.out.println("=====================================");
                System.out.println("===============>J-Cash<==============");
                System.out.println("=====================================");
                System.out.println("[1] Balance");
                System.out.println("[2] Deposit");
                System.out.println("[3] Transfer");
                System.out.println("[4] View Transaction");
                System.out.println("[5] Logout");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        uc.displayBalance();
                        break;
                    case 2:
                        uc.cashIn();
                        break;
                    case 3:
                        uc.transferMoney();
                        break;
                    case 4:
                        uc.displayTransactionLogs();
                        break;
                    case 5:
                        System.out.println();
                        return true;
                    default:
                        System.out.println("Thank you");
                        return false;
                }
            }while(true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}