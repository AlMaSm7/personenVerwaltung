package ch.bbw.personenverwaltung.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static String convertDate(Date date) throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2  = new SimpleDateFormat("dd.MM.yyyy");
        Date date2 = formatter1.parse(date.toString());
        return formatter2.format(date2);
    }
}
