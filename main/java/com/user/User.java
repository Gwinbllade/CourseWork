package com.user;

import com.command.Command;
import com.command.CommandResult;
import com.command.DisplayCommand;
import javafx.collections.ObservableList;


import java.util.HashMap;
import java.util.Map;


public class User {
    protected String loginUser; //, name, surname, Patronymic,PhoneNumber;
    protected final Map<Integer,Command> commands;
    protected final Map<Integer, DisplayCommand> displayCommand;

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
    public String getLoginUser() {
        return loginUser;
    }


    public User(){
        commands = new HashMap<>();
        displayCommand = new HashMap<>();

    }


    public void setCommands(int button, Command command){
        commands.put(button,command);
    }

    public void setDisplayCommand(int button, DisplayCommand command){
        displayCommand.put(button,command);
    }

    public CommandResult<String> pressButton(int button, String [] args){
        return commands.get(button).execute(args);
    }

     public ObservableList<Object> displayInfo(int button, String [] args){
        return displayCommand.get(button).execute(args);
    }



}

