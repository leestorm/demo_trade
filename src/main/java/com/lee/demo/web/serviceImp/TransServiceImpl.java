package com.lee.demo.web.serviceImp;

import com.lee.demo.web.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransServiceImpl implements TransactionService {

//    @Resource
//    private SysUserMapper  userMapper;

    @Override
    public List<String> querySyserList() {

        List userList = new ArrayList(Arrays.asList("Tom","Jack","Nick"));

        return userList;
    }
}
