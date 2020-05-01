package org.academiadecodigo.ramsters.sql;

import org.academiadecodigo.ramsters.datahandler.DataOptions;

import java.sql.*;
import java.util.*;

public class ReadDB {

    private ConnectionManager connectionManager;
    private Connection connection;

    public ReadDB() {
        connectionManager = new ConnectionManager();
        connection = connectionManager.getConnection();
    }

    public LinkedList<Integer> getTotals(){

        LinkedList<Integer> totals = new LinkedList<>();
        String checkTotal = "SELECT deaths, recovered, confirmed FROM covid_data WHERE country=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(checkTotal);
            preparedStatement.setString(1, "Total");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                totals.add(resultSet.getInt(1));
                totals.add(resultSet.getInt(2));
                totals.add(resultSet.getInt(3));
            }

            return totals;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totals;
    }

    public LinkedList<String> getTop(DataOptions dataOptions, int numberOfSelects) {

        LinkedList<String> result = new LinkedList<>();
        String aux = "";
        SortedMap<Integer, String> map = new TreeMap<>();

        String query = QueryBuilder.getTop(dataOptions);

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, numberOfSelects);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                map.put(resultSet.getInt(2), resultSet.getString(1));
                aux += resultSet.getString(1);
                int numb = resultSet.getInt(2);

                // Text from simplegraphics doesnt respect spaces;
               /* while(aux.length() + String.valueOf(numb).length() < 30){
                    aux += " ";
                }*/
                aux += ":" + numb;
                result.add(aux);
                aux = "";
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SortedMap <Integer, String> getTopWithPortugal(DataOptions dataOptions, int number) {
        SortedMap<Integer, String> map = new TreeMap<>();

        String query1;


        try {
            query1 = QueryBuilder.getPortugal(dataOptions);
            PreparedStatement statement = connection.prepareStatement(query1);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                map.put(resultSet.getInt(2), resultSet.getString(1));
            }

            String query2 = QueryBuilder.getTop(dataOptions);
            statement = connection.prepareStatement(query2);
            statement.setInt(1, number);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if(map.size() < number - 1){
                    map.put(resultSet.getInt(2), resultSet.getString(1));
                }
            }





        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
/*        Set<Integer> teste = map.keySet();
        for(Integer n : teste){
            System.out.println(map.get(n) + " : " + n);*/
        //}
    }
}
