package ch.bbw.personenverwaltung.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConverter {
    public static String convertDate(LocalDate date) throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd.MM.yyyy");
        Date date2 = formatter1.parse(date.toString());
        return formatter2.format(date2);
    }

    public static LocalDate dbDate(LocalDate date) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        date = LocalDate.parse(date.format(formatter));
        System.out.println(date.toString());
        if(date.getYear() > 2005){
            System.out.println("here");
            throw new Exception("person is to young");
        }
        return date;
    }
}
