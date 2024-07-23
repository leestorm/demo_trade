package com.lee.demo.web.command;

import com.lee.demo.web.entity.Transaction;

public abstract class Command {

    private Transaction transaction;

    public Command(Transaction transaction){

        this.transaction = transaction;

    }

    public Transaction getCommand(){

        return transaction;
    }

    public void setCommand(Transaction transaction){
        this.transaction = transaction;

    }

    public  abstract void execute(Transaction transaction);

}
