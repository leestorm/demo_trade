package com.lee.demo.web.command;

import com.lee.demo.web.entity.ProductStore;
import com.lee.demo.web.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class CancelCommand extends Command{

    private static final Logger log = LoggerFactory.getLogger(CancelCommand.class);

    public CancelCommand(Transaction transaction){
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
            subStore.remove(transaction.getTradeId());
        else if(transaction.getDirection().equals("Sell")){
            subStore.remove(transaction.getTradeId());
        }

        ProductStore.productStore.put(transaction.getProduct(),subStore);


        log.info("Execute Cancel Command:{}",transaction.toString());
    }


}
