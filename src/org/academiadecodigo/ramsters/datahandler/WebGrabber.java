package org.academiadecodigo.ramsters.datahandler;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebGrabber {

    private DataParser dataParser = new DataParser();

    public void grab(DataOptions dataOptions) {
        try {
            /**
             * latest
             * output {"confirmed":1095917,"deaths":58787,"recovered":225796}
             * finish
             * deaths
             * { "latest": 18625, "locations": [ ... ] }
             * total for each country in last
             *
             */


            //URL url = new URL("http://covid19api.herokuapp.com/confirmed");
            URL url = new URL("http://covid19api.herokuapp.com/" + dataOptions.getOption());

            URLConnection connection = url.openConnection();
            InputStream inputFromWeb = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFromWeb));

            String data;

            LinkedList<String> cenas = new LinkedList<>();

            while ((data = reader.readLine()) != null) {
                data = data.replace(",", "");
                data = data.replace("[", "");
                data = data.replace("]", "");
                data = data.replace("\"coordinates\":", "");
                cenas.addAll(Arrays.asList(data.split("[{\\}]+(?!=})")));
            }

            //System.out.println(cenas.get(1));


            dataParser.simpleParser(cenas, dataOptions);

            //cenas.forEach(System.out::println);

        } catch (MalformedURLException e) {
            System.out.println("url not formed correctly");
        } catch (IOException e) {
            System.out.println("Could not connect to external source");
        }

    }


    /**
     * Return a list of Strings with the Resume aka Totals
     * index 0 : confirmed
     * index 1 : deaths
     * index 2 : recovered
     */

    public List<String> grabResume() {

        try {
            URL resumeUrl = new URL("http://covid19api.herokuapp.com/latest");

            URLConnection connection = resumeUrl.openConnection();
            InputStream inputFromWeb = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFromWeb));

            String data;
            List<String> resume = new LinkedList<>();
            while ((data = reader.readLine()) != null) {

                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(data);
                while (m.find()) {
                    resume.add(m.group());
                }
            }

            return resume;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
