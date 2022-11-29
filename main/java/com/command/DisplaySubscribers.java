package com.command;

import com.database.DataBase;
import javafx.collections.ObservableList;

public class DisplaySubscribers implements DisplayCommand{
    DataBase database;

    public DisplaySubscribers(DataBase database) {
        this.database = database;
    }

    @Override
    public ObservableList<Object> execute(String[] args) {

        return database.displaySubscribers(args);
    }
}
