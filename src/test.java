import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    public static void main(String[] args) {

        String a = "{\"coordinates\":{\"latitude\":39.3999,\"longitude\":-8.2245},\"country\":\"Portugal\",\"country_code\":\"PT\",\"history\":{\"1/22/20\":0,\"1/23/20\":0,\"1/24/20\":0,\"1/25/20\":0,\"1/26/20\":0,\"1/27/20\":0,\"1/28/20\":0,\"1/29/20\":0,\"1/30/20\":0,\"1/31/20\":0,\"2/1/20\":0,\"2/10/20\":0,\"2/11/20\":0,\"2/12/20\":0,\"2/13/20\":0,\"2/14/20\":0,\"2/15/20\":0,\"2/16/20\":0,\"2/17/20\":0,\"2/18/20\":0,\"2/19/20\":0,\"2/2/20\":0,\"2/20/20\":0,\"2/21/20\":0,\"2/22/20\":0,\"2/23/20\":0,\"2/24/20\":0,\"2/25/20\":0,\"2/26/20\":0,\"2/27/20\":0,\"2/28/20\":0,\"2/29/20\":0,\"2/3/20\":0,\"2/4/20\":0,\"2/5/20\":0,\"2/6/20\":0,\"2/7/20\":0,\"2/8/20\":0,\"2/9/20\":0,\"3/1/20\":0,\"3/10/20\":41,\"3/11/20\":59,\"3/12/20\":59,\"3/13/20\":112,\"3/14/20\":169,\"3/15/20\":245,\"3/16/20\":331,\"3/17/20\":448,\"3/18/20\":448,\"3/19/20\":785,\"3/2/20\":2,\"3/20/20\":1020,\"3/21/20\":1280,\"3/22/20\":1600,\"3/23/20\":2060,\"3/24/20\":2362,\"3/25/20\":2995,\"3/26/20\":3544,\"3/27/20\":4268,\"3/28/20\":5170,\"3/29/20\":5962,\"3/3/20\":2,\"3/30/20\":6408,\"3/31/20\":7443,\"3/4/20\":5,\"3/5/20\":8,\"3/6/20\":13,\"3/7/20\":20,\"3/8/20\":30,\"3/9/20\":30,\"4/1/20\":8251,\"4/2/20\":9034,\"4/3/20\":9886,\"4/4/20\":10524,\"4/5/20\":11278},\"latest\":11278,\"province\":\"nan\"}";

        Pattern p = Pattern.compile("(\\{(?:\\[??[^\\[]*?\\}))");

        Matcher m = p.matcher(a);

        String b = "";

        while (m.find()) {
            b = m.group();
        }

        final String OLD_FORMAT = "MM/dd/yy";
        final String NEW_FORMAT = "dd-MM-yy";


        String[] aa = b.split(",");
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);

        for (int i = 0; i < aa.length; i++) {
            if(i == 0 || i == aa.length){
                aa[i] = aa[i].replace("{", "");
                aa[i] = aa[i].replace("}", "");
            }
            aa[i] = aa[i].replace("\"", "");
            String[] aux = aa[i].split(":");
            try {
                System.out.println(aux[0]);
                Date auxb = sdf.parse(aux[0]);
                sdf.applyPattern(NEW_FORMAT);
                aa[i] = sdf.format(auxb) +":"+ aux[1];
            } catch (ParseException e) {
                System.out.println("shieeeet");
            }
        }

        System.out.println(b);

        String[] bb = a.split("\\[([^]]+)\\]");

        for(String t : aa){
            System.out.println(t);
        }
    }
}
