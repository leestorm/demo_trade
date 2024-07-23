package com.lee.demo.web.command;

import com.lee.demo.web.entity.ProductStore;
import com.lee.demo.web.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class InsertCommand extends Command{

    private static final Logger log = LoggerFactory.getLogger(InsertCommand.class);

    public InsertCommand(Transaction transaction){
        super(transaction);
    }

    @Override
    public void execute(Transaction transaction){

        if(!ProductStore.productStore.containsKey(transaction.getProduct())){
            ProductStore.productStore.put(transaction.getProduct(),new ConcurrentHashMap<>());
        }

        ConcurrentHashMap<String, Long> subStore = ProductStore.productStore.get(transaction.getProduct());

        if(!subStore.containsKey(transaction.getTradeId())){
            subStore.put(transaction.getTradeId(),0L);
        }

        if(transaction.getDirection().equals("Buy"))
            subStore.put(transaction.getTradeId(),subStore.get(transaction.getTradeId())+transaction.getQuantity());
        else if(transaction.getDirection().equals("Sell")){
            subStore.put(transaction.getTradeId(),subStore.get(transaction.getTradeId())-transaction.getQuantity());
        }

        ProductStore.productStore.put(transaction.getProduct(),subStore);


        log.info("Execute Insert Command:{}",transaction.toString());
    }


}
