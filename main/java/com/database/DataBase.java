package com.database;

import com.command.CommandResult;
import com.tariff.Tariff;
import com.user.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Objects;

public class DataBase {
    private static Connection connection;



    public CommandResult<String> connectionToBD(String[] args)  {
        String URL = args[0];
        String USERNAME = args[1];
        String PASSWORD = args[2];
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            return new CommandResult<String>("Connect failed","user",false);
        }
        return new CommandResult<String>("Connect successful!","user",true);
    }


    public CommandResult<String> addTariff(String[] args) {

        for(int i = 0; i< args.length - 1;i++) {
            if(Objects.equals(args[i], "")) {
                return new CommandResult<String>("Incorrectly entered data","admin",false);
            }
        }

        try {
            Integer.parseInt(args[2]);
            Integer.parseInt(args[3]);
            Integer.parseInt(args[4]);
            Integer.parseInt(args[5]);

        } catch (Exception e){
            return new CommandResult<String>("Incorrectly entered data","admin",false);
        }

        try {
            Double.parseDouble(args[1]);
            Double.parseDouble(args[6]);
            Double.parseDouble(args[7]);
            Double.parseDouble(args[8]);
        }
        catch (Exception e){
            return new CommandResult<String>("Incorrectly entered data","admin",false);
        }

        if (Objects.equals(args[9], "")){
            args[9] = "None";
        }

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO Tariff " +
                            "VAlUES('%s',%s,%s, %s,%s, %s, %s,  %s, %s,'%s')"
                    , args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]));
        } catch (SQLException e) {
            return new CommandResult<String>("A tariff with this name exists","admin",false);
        }
        return new CommandResult<String>("Add tariff succeed","admin",true);

    }


    public CommandResult<String> chooseTariff(String[] args){
        String LoginUser = args[0];
        String TariffId = args[1];

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate((String.format("UPDATE Client SET TariffID = %s WHERE [PhoneNumber] = '%s'",TariffId,LoginUser) ) );
            return new CommandResult<String>("Tariff choose succeed",LoginUser,true);

        } catch (SQLException e) {
            return new CommandResult<String>("Tariff choose  false!",LoginUser,false);
        }

    }


    public CommandResult<String>  editTariff(String[] args){
        for(int i = 0; i< args.length - 2;i++) {
            if(Objects.equals(args[i], "")) {
                return new CommandResult<String>("Incorrectly entered data(1)","admin",false);
            }
        }

        try {
            Integer.parseInt(args[2]);
            Integer.parseInt(args[3]);
            Integer.parseInt(args[4]);
            Integer.parseInt(args[5]);

        } catch (Exception e){
            return new CommandResult<String>("Incorrectly entered data","admin",false);
        }

        try {
            Double.parseDouble(args[1]);
            Double.parseDouble(args[6]);
            Double.parseDouble(args[7]);
            Double.parseDouble(args[8]);
        }
        catch (Exception e){
            return new CommandResult<String>("Incorrectly entered data","admin",false);
        }

        if (Objects.equals(args[9], "")){
            args[9] = "None";
        }

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("""
                            UPDATE Tariff SET [Name] = '%s',
                            SubPrice = %s,
                            MinutesByOperator = %s,
                            MinutesOtherOperators = %s,
                            SMS = %s,
                            MB = %s,
                            PriceExtraMinute = %s,
                            PriceExtraSMS = %s,
                            PriceExtra_100MB = %s,
                            SpecialCondition = '%s'
                            WHERE TariffID = %s"""
                    , args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10]));
        } catch (SQLException e) {
            return new CommandResult<String>("A tariff with this name exists","admin",false);
        }
        return new CommandResult<String>("Edit tariff succeed","admin",true);


    }


    public CommandResult<String> deleteTariff(String[] args) {
        String ID = args[1];
        String LoginUser = args[0];

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("DELETE FROM Tariff " +
                    "WHERE TariffID = %s", ID));
            return new CommandResult<String>("Delete succeed!", LoginUser, true);

        } catch (SQLException e) {
            return new CommandResult<String>("Delete failed!", LoginUser, false);
        }

    }


    public ObservableList<Object> displaySubscribers(String[] args) {
        ObservableList<Object> subscribersList = FXCollections.observableArrayList();
        ResultSet resultSet;



        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT [ClientID],[Surname],c.[Name],[Patronymic],[PhoneNumber],t.[Name], c.[TariffID] " +
                    "FROM Client as c Left Join Tariff as t ON c.TariffID = t.TariffID");


            while(resultSet.next()){
                subscribersList.add (new Subscriber(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),(resultSet.getString(6) == null? "None" : resultSet.getString(6)),
                        (resultSet.getString(7) == null? "None" : resultSet.getString(7))));

            }
            return subscribersList;

        } catch (SQLException e) {
            return null;
        }

    }


    public CommandResult<String> register(String[] args){
        String name = args[0],surname = args[1], patronymic = args[2],phoneNumber = args[3],password = args[4];


        ResultSet resultSet;

        for ( int i = 0; i < args.length;i++){
            if (Objects.equals(args[0], "")) {
                return new CommandResult<String>("Incorrectly entered data","user",false);
            }
        }


        if (!(phoneNumber.matches("38(\\d{10})"))){
            return new CommandResult<String>("The number was entered incorrectly","user",false);
        }



        try {
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(String.format("SELECT COUNT(*) FROM Client " +
                    "WHERE PhoneNumber = '%s'",phoneNumber));
            resultSet.next();

            if(resultSet.getInt(1) == 0) {

                statement.executeUpdate(String.format("INSERT INTO Client " +
                        "VALUES('%s','%s','%s','%s','%s',null)",password,phoneNumber,surname,name,patronymic));
                return new CommandResult<String>("Register succeed!","user",true);

            }
            else {
                return new CommandResult<String>("Register failed(The user with this number is registered)","user",false);
            }
        } catch (Exception e) {
            return new CommandResult<String>("Register failed!","user",false);
        }



    }


    public CommandResult<String> logIn(String[] args){
        ResultSet resultSet;
        String login = args[0], password =  args[1];
        try {
            Statement statement = connection.createStatement();

            if (Objects.equals(login, "admin") && Objects.equals(password, "12345678")){
                return new CommandResult<String>("Log in admin succeed!","admin",true);
            }

            resultSet = statement.executeQuery(String.format("SELECT COUNT(ClientID) FROM  Client " +
                    "WHERE Password = '%s' and PhoneNumber = '%s'", password,login));
            resultSet.next();


            if(resultSet.getInt(1) == 1) {
                return new CommandResult<String>("Log in subscribers succeed!",login,true);

            }
            else{
                return new CommandResult<String>("The error is an incorrect login or password","user",false);
            }

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public CommandResult<String> logOut(String[] args){
        String LoginUser = args[0];
        try {
            connection.close();
        } catch (SQLException e) {
            return new CommandResult<String>("Log out failed!",LoginUser,false);
        }
        return new CommandResult<String>("Log out succeed!",LoginUser,true);
    }


    public String[]  userInformation(String userLogin) {

        ResultSet resultSet;


        try {
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(String.format("SELECT * FROM [Client] " +
                    "WHERE PhoneNumber = '%s' ", userLogin));
            resultSet.next();

            String [] info = new String[]{resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6), resultSet.getString(7), "None"};

            if (!Objects.equals(info[6], null)){

                resultSet = statement.executeQuery(String.format("SELECT [Name] FROM [Tariff] " +
                        "WHERE TariffID = %s ", info[6]));
                resultSet.next();

                resultSet.getString(1);
                info[7] = resultSet.getString(1);
            }

            return info;
        } catch (SQLException e) {
            return new String[] {"null","null","null","null","null","null","null","null"};

        }

    }


    public ObservableList <Object> displayTariff(String [] args){
        ObservableList<Object> tariffList = FXCollections.observableArrayList();
        String maxPrice,minPrice,numberOfSMS,minutesByOperator,minutesOtherOperator,mobileDataMB;

        try {
            minPrice = (!Objects.equals(args[0], "") ? args[0] : "0");
            maxPrice =(!Objects.equals(args[1], "") ? args[1] : "100000");
            numberOfSMS =(!Objects.equals(args[2], "") ? args[2] : "0");
            minutesByOperator = (!Objects.equals(args[3], "") ? args[3] : "0");
            minutesOtherOperator = (!Objects.equals(args[4], "") ? args[4] : "0");
            mobileDataMB = (!Objects.equals(args[5], "") ? args[5] : "0");

            ResultSet resultSet;

            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(String.format("SELECT * FROM Tariff " +
                            "WHERE [SubPrice]>=%s AND [SubPrice]<=%s AND [SMS] >= %s AND [MinutesByOperator]>=%s AND [MinutesOtherOperators]>=%s AND [MB]>=%s ",
                    minPrice,maxPrice,numberOfSMS,minutesByOperator,minutesOtherOperator,mobileDataMB));

            while (resultSet.next()) {
                tariffList.add(new Tariff(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),
                        resultSet.getString(10), resultSet.getString(11)));

            }

            return  tariffList;

        } catch (Exception e) {
            return  null;
        }
    }
}

