package com.command;

import com.database.DataBase;
import javafx.collections.ObservableList;

public class DisplayTariff implements DisplayCommand{
    DataBase database;

    public DisplayTariff(DataBase database) {
        this.database = database;
    }

    @Override
    public ObservableList<Object> execute(String[] args) {
        return database.displayTariff(args);
    }

}
