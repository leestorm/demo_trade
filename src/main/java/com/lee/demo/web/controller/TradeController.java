package com.lee.demo.web.controller;

import com.lee.demo.web.command.Command;
import com.lee.demo.web.command.CommandFactory;
import com.lee.demo.web.command.Invoker;
import com.lee.demo.web.entity.ProductStore;
import com.lee.demo.web.entity.Transaction;
import com.lee.demo.web.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/trade")
@Slf4j
public class TradeController {

    @Autowired
    private TransactionService sysUserService;

    @Autowired
    private Invoker invoker;


    /**
     * 1,1,1,REL,50,INSERT,Buy
     * 2,2,1,ITC,40,INSERT,Sell
     * 3,3,1,INF,70,INSERT,Buy
     * 4,1,2,REL,60,UPDATE,Buy
     * 5,2,2,ITC,30,CANCEL,Buy
     * 6,4,1,INF,20,INSERT,Sell
     */

    @RequestMapping(value = "/executeCommand",method = RequestMethod.GET)
    public String executeCommand(@RequestParam(name="commandStr",required = true)String commandStr){

        log.info("Command String is {}", commandStr);

        Transaction transaction = this.commandToTransaction(commandStr);

        Command command =  CommandFactory.createCommand(transaction);
        invoker.setCommand(command);
        invoker.executeCommand();

        HashMap<String,Long> result = calAllPosition();

        return result.toString();
    }

    private Transaction commandToTransaction(String commandStr){

        Transaction transaction = new Transaction();
        String [] transactionStr = commandStr.split(",");
        transaction.setID(Long.valueOf(transactionStr[0]));
        transaction.setTradeId(transactionStr[1]);
        transaction.setVersion(Integer.parseInt(transactionStr[2]));
        transaction.setProduct(transactionStr[3]);
        transaction.setQuantity(Long.valueOf(transactionStr[4]));
        transaction.setAction(transactionStr[5]);
        transaction.setDirection(transactionStr[6]);

        return transaction;
    }


    private HashMap<String,Long> calAllPosition(){

        HashMap<String, Long> result = new HashMap<String,Long>();


        for (Map.Entry<String, ConcurrentHashMap<String, Long>> entry : ProductStore.productStore.entrySet()) {

            if(result.containsKey(entry.getKey())){
                //
            }else{
                result.put(entry.getKey(),0L);
            }
            String productName = entry.getKey();

            for(Map.Entry<String, Long> subEntry : entry.getValue().entrySet()){
                result.put(productName,result.get(productName)+subEntry.getValue());
            }

        }

        return result;
    }


}
