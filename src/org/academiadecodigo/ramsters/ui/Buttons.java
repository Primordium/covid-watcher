package org.academiadecodigo.ramsters.ui;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Buttons {

    private Rectangle buttonBar;

    public Buttons() {
        buttonBar = new Rectangle(10, 610, 800, 100);
        buttonBar.setColor(Color.GREEN);
        buttonBar.draw();

        init();
    }

    private void init() {
        Rectangle button1 = new Rectangle(50, 630, 200, 60);
        Rectangle button2 = new Rectangle(300, 630, 200, 60);
        Rectangle button3 = new Rectangle(550, 630, 200, 60);

        button1.setColor(Color.RED);
        button2.setColor(Color.YELLOW);
        button3.setColor(Color.GREEN);

        button1.draw();
        button2.draw();
        button3.draw();

    }

}
