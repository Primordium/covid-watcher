package org.academiadecodigo.ramsters.ui;

import org.academiadecodigo.ramsters.datahandler.DataOptions;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

import java.util.LinkedList;


public class SideBar extends AbstractUI implements Watchers {

    private Rectangle sideBar;
    private Text total;
    private Text numbers;
    private LinkedList<Text> textLinkedList = new LinkedList<>();

    public SideBar() {
        init();
    }

    public void init() {

        sideBar = new Rectangle(810, 10, 200, 700);
        sideBar.setColor(Color.RED);
        sideBar.draw();

        total = new Text(900, 30, "TOTAL");
        total.setColor(Color.RED);
        total.grow(75, 30);
        total.draw();

        LinkedList<Integer> totalNumbers = getReader().getTotals();
        String aux = "";
        for (Integer a : totalNumbers) {
            aux += a.toString() + "      ";
        }

        Text drc = new Text(820, 80, "Deaths / Recovered / Confirmed");
        drc.setColor(Color.WHITE);
        drc.draw();

        numbers = new Text(825, 100, aux);
        numbers.setColor(Color.WHITE);
        numbers.draw();
        walloftext(DataOptions.DEATHS);

    }

    public void walloftext(DataOptions dataOptions) {

        for (Text t : textLinkedList) {
            t.delete();
        }


        Text countryAndOption = new Text(825, 135, "");

        switch (dataOptions) {
            case RECOVERED:
                countryAndOption = new Text(825, 135, "Country                       Recovered");
                countryAndOption.setColor(Color.GREEN);
                break;
            case DEATHS:
                countryAndOption = new Text(825, 135, "Country                             Deaths");
                countryAndOption.setColor(Color.RED);
                break;
            case CONFIRMED:
                countryAndOption = new Text(825, 135, "Country                       Confirmed");
                countryAndOption.setColor(Color.YELLOW);
        }

        countryAndOption.draw();
        textLinkedList.add(countryAndOption);

        LinkedList<String> info = getReader().getTop(dataOptions, 50);

        /*        Set<Integer> teste = map.keySet();
        for(Integer n : teste){
            System.out.println(map.get(n) + " : " + n);*/
        //}
        //Set<Integer> keys = info.keySet();


        for (int i =0; i<info.size() ;i++) {
            String[] aux = info.get(i).split(":");
            Text display = new Text(825, (150 + i * 11), aux[0]);

            switch (aux[1].length()) {
                case 1:
                    aux[1] = "\u205F\u205F\u205F\u205F\u205F\u205F\u205F\u205F\u2009\u2009" + aux[1];
                    break;
                case 2:
                    aux[1] = "\u205F\u205F\u205F\u205F\u205F\u205F\u205F\u2009" + aux[1];
                    break;
                case 3:
                    aux[1] = "\u205F\u205F\u205F\u205F\u205F\u2009" + aux[1];
                    break;
                case 4:
                    aux[1] = "\u205F\u205F\u205F\u2009" + aux[1];
                    break;
                case 5:
                    aux[1] = "\u205F\u205F" + aux[1];
                    break;
            }

            Text display2 = new Text(955, (150 + i * 11), aux[1]);
            display.setColor(Color.LIGHT_GRAY);
            display2.setColor(Color.LIGHT_GRAY);
            if (aux[0].equals("Portugal")) {
                display.setColor(Color.ORANGE);
                display2.setColor(Color.ORANGE);
            }

            display.draw();
            display2.draw();
            textLinkedList.add(display);
            textLinkedList.add(display2);
            setDataOptions(dataOptions);

        }

    }

    @Override
    public void update(DataOptions dataOptions) {
        if (dataOptions == DataOptions.REFRESH){
            walloftext(getDataOptions());
        }else {
            walloftext(dataOptions);
        }
    }
}
