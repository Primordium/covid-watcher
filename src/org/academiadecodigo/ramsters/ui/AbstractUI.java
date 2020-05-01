package org.academiadecodigo.ramsters.ui;

import org.academiadecodigo.ramsters.datahandler.DataOptions;
import org.academiadecodigo.ramsters.sql.ReadDB;

public abstract class AbstractUI {

    private DataOptions dataOptions;
    private static ReadDB reader = new ReadDB();

    public static ReadDB getReader() {
        return reader;
    }

    public DataOptions getDataOptions() {
        return dataOptions;
    }

    public void setDataOptions(DataOptions dataOptions) {
        this.dataOptions = dataOptions;
    }
}
