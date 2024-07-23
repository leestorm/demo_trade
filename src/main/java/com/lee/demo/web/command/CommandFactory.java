package com.lee.demo.web.command;

import com.lee.demo.web.entity.Transaction;

public class CommandFactory {

    public static Command createCommand(Transaction transaction) {
        switch (transaction.getAction()){
            case "INSERT":
                return new InsertCommand(transaction);
            case "UPDATE":
                return new UpdateCommand(transaction);
            case "CANCEL":
                return new CancelCommand(transaction);
            default:
                throw new IllegalArgumentException("Unsupported Command Type :"+transaction.getAction());
        }


    }
}
