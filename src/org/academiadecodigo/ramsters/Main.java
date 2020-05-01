package org.academiadecodigo.ramsters;

import org.academiadecodigo.ramsters.ui.Frame;
import org.academiadecodigo.ramsters.ui.MouseOnButtons;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {


    private static UpdateData updateData = new UpdateData();

    public static void main(String[] args) {
        Frame frame = new Frame(updateData);

        final ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        schedule.scheduleAtFixedRate(updateData, 0, 10, TimeUnit.MINUTES);


    }

}
