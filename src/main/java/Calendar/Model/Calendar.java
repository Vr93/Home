package Calendar.Model;

public class Calendar {

    public Calendar(){

    }


    public static String getCalendarIframe(){
        return "<iframe src=\"https://calendar.google.com/calendar/" +
                "embed?title=Vegard&amp;showDate=0&amp;showPrint=0&amp;" +
                "showCalendars=0&amp;showTz=0&amp;mode=WEEK&amp;height=500" +
                "&amp;wkst=2&amp;hl=no&amp;bgcolor=%23ffffff&amp;src=vegardrognee" +
                "%40gmail.com&amp;color=%23711616&amp;ctz=Europe%2FOslo\" style=\"border-width:0\"" +
                " width=\"100%\" height=\"500\" frameborder=\"0\" scrolling=\"no\"></iframe>";
    }


}
