package com.lee.demo.web.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Transaction {

    //Product ID
    private Long ID;

    private String tradeId;

    private int version;

    private String product;

    private Long quantity;

    private String action;

    private String direction;




}
