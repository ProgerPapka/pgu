package webservice.objects.time;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;

public class TimeStampUtil {

    public static XMLGregorianCalendar getXMLGregorianCalendar(LocalDateTime localDateTime){
        XMLGregorianCalendarImpl calendar = new XMLGregorianCalendarImpl();
        calendar.setDay(localDateTime.getDayOfMonth());
        calendar.setYear(localDateTime.getYear());
        calendar.setMonth(localDateTime.getMonthValue());
        calendar.setHour(localDateTime.getHour());
        calendar.setMinute(localDateTime.getMinute());
        calendar.setSecond(localDateTime.getSecond());
        return calendar;
    }

}
