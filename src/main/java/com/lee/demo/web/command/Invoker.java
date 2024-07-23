package com.lee.demo.web.command;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand(){
        this.command.execute(command.getCommand());

    }
}
