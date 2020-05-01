package org.academiadecodigo.ramsters.ui;

import javafx.scene.input.PickResult;
import org.academiadecodigo.ramsters.datahandler.DataOptions;
import org.academiadecodigo.ramsters.datahandler.NameConverter;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.*;
import java.util.regex.Matcher;


public class Graph extends AbstractUI implements Watchers {

    private Rectangle graph;
    private SortedMap<Integer, String> values;
    private int maxValue;
    private LinkedList<Rectangle> barList;
    private LinkedList<Text> barNames;
    private final int LIMIT = 569;
    private NameConverter nameConverter;
    private Picture picture;


    public Graph() {
        init();
        setDataOptions(DataOptions.DEATHS);
        draw(getDataOptions());
    }

    private void init() {
        graph = new Rectangle(40, 40, 730, 530);
        graph.draw();
        picture = new Picture(40,40, "resources/sars-cov-19.bmp");
        picture.draw();
        graph.setColor(Color.BLUE);
        barList = new LinkedList<>();
        barNames = new LinkedList<>();
        nameConverter = new NameConverter();
    }

    public void draw(DataOptions dataOptions) {
        for(Rectangle a : barList){a.setColor(Color.BLACK); a.draw();}
        for(Text a : barNames){a.delete();}
        Color color = Color.WHITE;
        switch (dataOptions){
            case CONFIRMED:
                color = Color.YELLOW;
                break;
            case DEATHS:
                color = Color.RED;
                break;
            case RECOVERED:
                color = Color.GREEN;
                break;
        }


        int xPos = 50;
        values = getReader().getTopWithPortugal(dataOptions, 26);
        Set<Integer> keys = values.keySet();
        maxValue = (int) (values.lastKey() * (1 + (Math.random()/10)));
        for (Integer n : keys) {
            int aux = getHeight(n);
            Rectangle rectangle = new Rectangle(xPos, (LIMIT - aux), 20, aux);
            rectangle.setColor(color);

            Text text = new Text(xPos + 3, LIMIT + 5, nameConverter.convertName(values.get(n)));
            text.setColor(color);

            if(nameConverter.convertName(values.get(n)) == "PT"){
                rectangle.setColor(Color.ORANGE);
                text.setColor(Color.ORANGE);
            }

            text.draw();
            rectangle.fill();
            xPos += 29;
            barNames.add(text);
            barList.add(rectangle);
        }
            setDataOptions(dataOptions);
    }


    public int getHeight(int a) {
        a *= 500;
        a /= maxValue;
        if (a == 0) {
            a = 1;
        }
        return a;
    }

    @Override
    public void update(DataOptions dataOptions) {
        if (dataOptions == DataOptions.REFRESH) {
            draw(getDataOptions());
        }
        draw(dataOptions);
    }
}
