package org.academiadecodigo.ramsters.ui;

import org.academiadecodigo.ramsters.UpdateData;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Frame {

    private Rectangle base = new Rectangle(10,10, 1000, 700);
    private SideBar sideBar;
    private Buttons buttons;
    private Graph graph;
    private MouseOnButtons mouse;

    public Frame(UpdateData updateData) {
        base.setColor(Color.BLACK);
        base.fill();
        sideBar = new SideBar();
        buttons = new Buttons();
        graph = new Graph();
        mouse = new MouseOnButtons();
        mouse.attach(sideBar);
        mouse.attach(graph);

        updateData.attach(sideBar);
        updateData.attach(graph);

    }

}
