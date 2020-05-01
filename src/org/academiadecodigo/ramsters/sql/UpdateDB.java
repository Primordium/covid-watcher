package org.academiadecodigo.ramsters.sql;


import org.academiadecodigo.ramsters.datahandler.DataOptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedMap;

public class UpdateDB {

    private ConnectionManager connectionManager;
    private Connection connection;

    public UpdateDB() {
        connectionManager = new ConnectionManager();
        connection = connectionManager.getConnection();

    }

    public boolean insertData(SortedMap<String, Integer> data, DataOptions dataOptions) {


        data.entrySet().forEach(entry -> {

                try {
                    String checkCountry = "SELECT country FROM covid_data WHERE country=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(checkCountry);
                    preparedStatement.setString(1, entry.getKey());

                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(!resultSet.next()) {
                        preparedStatement = connection.prepareStatement(QueryBuilder.getInsert(dataOptions));
                        preparedStatement.setString(1, entry.getKey());
                        preparedStatement.setInt(2, entry.getValue());
                        preparedStatement.executeUpdate();
                    }else{
                        preparedStatement = connection.prepareStatement(QueryBuilder.getUpdate(dataOptions));
                        preparedStatement.setInt(1, entry.getValue());
                        preparedStatement.setString(2, entry.getKey());
                        preparedStatement.executeUpdate();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            return true;
        }
    }
