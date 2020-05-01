package org.academiadecodigo.ramsters.datahandler;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.academiadecodigo.ramsters.sql.*;

public class DataParser {

    private UpdateDB database = new UpdateDB();
    private SortedMap<String, Integer> output;

    public void simpleParser(LinkedList<String> linkedList, DataOptions dataOptions) {
        output = new TreeMap<>();
        String country = "";
        String deaths = "";

        for (int i = 1; i < linkedList.size(); i += 2) {

            if((country.length() > 0 )&&(deaths.length() > 0)) {
                //System.out.println(country + " : " + deaths);
                if(output.containsKey(country)){
                    output.replace(country, (output.get(country) + Integer.parseInt(deaths)));
                }else{
                    output.put(country, Integer.parseInt(deaths));
                }
                country = new String();
                deaths = new String();
            }

            if (linkedList.get(i).startsWith("\"country\"")) {
                String aux = linkedList.get(i).replace("\"country\":\"", "");
                country = (aux.split("\""))[0];
                continue;
            }
            if (linkedList.get(i).startsWith("\"latest\"") && i == 1) {
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(linkedList.get(i));
                m.find();
                deaths = m.group();
                country = "Total";
                continue;
            }

            if (linkedList.get(i).startsWith("\"latest\"")) {
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(linkedList.get(i));
                m.find();
                deaths = m.group();
                continue;
            }
        }

/*        output.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });*/

        database.insertData(output, dataOptions);

    }
}
