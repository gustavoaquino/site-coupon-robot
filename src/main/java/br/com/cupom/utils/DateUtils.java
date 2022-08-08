package br.com.cupom.utils;

import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date formatarData(String data) throws ParseException {
        final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada = formato.parse(data);
        return dataFormatada;
    }

    public static String parseDateExpiration(Date date){
        String newDate = StringUtils.replace(date.toString(), "00:00:00.0", "").trim();

        newDate = (newDate.charAt(8) + "" + newDate.charAt(9)) + "/"
                + (newDate.charAt(5) + "" + newDate.charAt(6)) + "/"
                + (newDate.charAt(0) + "" + newDate.charAt(1)  + "" + newDate.charAt(2) + "" + newDate.charAt(3));

        return newDate;
    }

}
