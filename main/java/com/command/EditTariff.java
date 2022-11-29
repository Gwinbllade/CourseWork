package com.command;
import com.database.DataBase;

public class EditTariff implements Command{
    DataBase database;

    public EditTariff(DataBase database) {
        this.database = database;
    }

    @Override
    public CommandResult<String> execute(String[] args) {

        return database.editTariff(args);
    }
}
