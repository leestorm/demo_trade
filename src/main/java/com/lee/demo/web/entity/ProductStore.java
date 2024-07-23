package com.lee.demo.web.entity;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ProductStore {

    public static ConcurrentHashMap<String, ConcurrentHashMap<String,Long>> productStore = new ConcurrentHashMap<>();

}
