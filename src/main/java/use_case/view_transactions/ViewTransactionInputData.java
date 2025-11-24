package use_case.view_transactions;

import entity.Transaction;

import java.util.ArrayList;

import java.time.LocalDate;


public class ViewTransactionInputData {

private static ArrayList<Transaction> monthlyTransactions;
private static String month = "11";

    public ViewTransactionInputData(ArrayList<Transaction> monthlyTransactions, String month) {
        this.monthlyTransactions = monthlyTransactions;
        this.month = month;
    }

    static String getMonth(){
        return month; //TODO: validate
    }
    static ArrayList<Transaction> getTransactionList(){
        return monthlyTransactions;
    }

}
