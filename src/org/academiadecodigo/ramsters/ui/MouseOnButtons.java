package org.academiadecodigo.ramsters.ui;

import org.academiadecodigo.ramsters.datahandler.DataOptions;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

import java.util.LinkedList;

public class MouseOnButtons implements MouseHandler {

    LinkedList<Watchers> watchers = new LinkedList<>();

    public MouseOnButtons() {
        Mouse mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

//         Rectangle button1 = new Rectangle(50, 630, 200, 60);
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getX() > 50 && mouseEvent.getX() < 250 && mouseEvent.getY() > 660 && mouseEvent.getY() < 725){
            notif(DataOptions.DEATHS);
        }
        if(mouseEvent.getX() > 310 && mouseEvent.getX() < 505 && mouseEvent.getY() > 660 && mouseEvent.getY() < 725){
            notif(DataOptions.CONFIRMED);
        }
        if(mouseEvent.getX() > 560 && mouseEvent.getX() < 755 && mouseEvent.getY() > 660 && mouseEvent.getY() < 725){
            notif(DataOptions.RECOVERED);
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public void attach(Watchers w){
        watchers.add(w);
    }

    private void notif(DataOptions options) {
        for(Watchers w : watchers){
            w.update(options);
        }

    }

}
