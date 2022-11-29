package com.command;

import javafx.collections.ObservableList;


public interface DisplayCommand {
    public ObservableList<Object> execute(String[] args);
}
