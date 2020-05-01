package org.academiadecodigo.ramsters.datahandler;

public enum DataOptions {

    CONFIRMED("confirmed"),
    DEATHS("deaths"),
    RECOVERED("recovered"),
    REFRESH("refresh");
   // LATEST("latest"),
   // UPDATEDAT("updatedAt");

    private String option;

    DataOptions(String option){
        this.option = option;
    }

    public String getOption() {
        return option;
    }

}

