import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day &&
                month == date.month &&
                year == date.year;
    }

    //Check if date is valid
    public static boolean isDateValid(String date){
        final String DATE_FORMAT = "dd-MM-yyyy";
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e){
            return false;
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    public int getDateInDays(){
        return (this.year*365)+(this.month*30)+(this.day);
    }

    @Override
    public String toString() {
        return  day +
                "/" + month +
                "/" + year;
    }

    public int compareTo(Date date) {
        {
            return (this.getDateInDays()-date.getDateInDays());
        }
    }


}
