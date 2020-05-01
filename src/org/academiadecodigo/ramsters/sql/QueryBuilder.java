package org.academiadecodigo.ramsters.sql;

import org.academiadecodigo.ramsters.datahandler.DataOptions;


public final class QueryBuilder {


    public static String getInsert(DataOptions dataOptions) {

        String query = "";
        switch (dataOptions) {
            case RECOVERED:
                query = "INSERT INTO covid_data (country, recovered) VALUES (?,?)";
                break;
            case DEATHS:
                query = "INSERT INTO covid_data (country, deaths) VALUES (?,?)";
                break;
            case CONFIRMED:
                query = "INSERT INTO covid_data (country, confirmed) VALUES (?,?)";
                break;
        }
        return query;
    }

    public static String getUpdate(DataOptions dataOptions) {

        String query = "";
        switch (dataOptions) {
            case RECOVERED:
                query = "UPDATE covid_data SET recovered=? WHERE country=?";
                break;
            case DEATHS:
                query = "UPDATE covid_data SET deaths=? WHERE country=?";
                break;
            case CONFIRMED:
                query = "UPDATE covid_data SET confirmed=? WHERE country=?";
                break;
        }
        return query;
    }

    public static String getTop(DataOptions dataOptions) {
        String query = "";
        switch (dataOptions){
            case CONFIRMED:
                query = "SELECT country, confirmed FROM covid_data ORDER BY confirmed DESC LIMIT ? OFFSET 1";
                break;
            case DEATHS:
                query = "SELECT country, deaths FROM covid_data ORDER BY deaths DESC LIMIT ? OFFSET 1";
                break;
            case RECOVERED:
                query = "SELECT country, recovered FROM covid_data ORDER BY recovered DESC LIMIT ? OFFSET 1";

                // query = "SELECT country, recovered FROM covid_data WHERE country = '%' AND country NOT IN (SELECT country FROM covid_data WHERE country = 'Portugal' ) ORDER BY recovered DESC LIMIT ? OFFSET 1";
        }
        return query;
    }

    public static String getPortugal(DataOptions dataOptions) {

        String query = "";
        switch (dataOptions) {
            case RECOVERED:
                query = "SELECT country, recovered FROM covid_data WHERE country='Portugal'";
                break;
            case DEATHS:
                query = "SELECT country, deaths FROM covid_data WHERE country='Portugal'";
                break;
            case CONFIRMED:
                query = "SELECT country, confirmed FROM covid_data WHERE country='Portugal'";
                break;
        }
            return query;
    }
}
