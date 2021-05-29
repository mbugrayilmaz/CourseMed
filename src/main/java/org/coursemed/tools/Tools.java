package org.coursemed.tools;

import java.text.DecimalFormat;

public class Tools {
    private Tools() {
    }

    public static String getEmbed(String url) {
        String domain = "https://www.youtube.com/embed/";

        String embed = url.substring(url.indexOf('=') + 1);

        return domain + embed;
    }

    public static String getFormattedBalance(double balance){
        DecimalFormat formatter=new DecimalFormat("#.##₺");

        return formatter.format(balance);
    }
}
