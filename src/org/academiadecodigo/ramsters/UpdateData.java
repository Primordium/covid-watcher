package org.academiadecodigo.ramsters;

import org.academiadecodigo.ramsters.datahandler.DataOptions;
import org.academiadecodigo.ramsters.datahandler.WebGrabber;
import org.academiadecodigo.ramsters.ui.Watchers;

import java.util.LinkedList;

public class UpdateData implements Runnable {

    WebGrabber webGrabber;
    LinkedList<Watchers> watchersLinkedList;

    public UpdateData() {
        webGrabber = new WebGrabber();
        watchersLinkedList = new LinkedList<>();
    }

    public void notifyWatchers() {
/*        for (Watchers w: watchersLinkedList) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("cenas");
            }
            w.update(DataOptions.REFRESH);
        }*/
    }

    public void attach(Watchers watchers) {
        watchersLinkedList.add(watchers);
    }

    @Override
    public void run() {

        webGrabber.grab(DataOptions.RECOVERED);
        webGrabber.grab(DataOptions.DEATHS);
        webGrabber.grab(DataOptions.CONFIRMED);

        notifyWatchers();
    }
}
