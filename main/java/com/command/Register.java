package com.command;

import com.database.DataBase;


public class Register implements Command {
    DataBase database;

    public Register(DataBase database) {
        this.database = database;
    }

    @Override
    public CommandResult<String> execute(String[] args) {
        return database.register(args);
    }
}






