package webtest.servlet;

import java.text.SimpleDateFormat;

public class Uploads {

    private String name;
    long date;
    long size;

    public Uploads(String name, long date, long size) {
        super();
        this.name = name;
        this.date = date;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        String formattedDate = String.valueOf(simpleDateFormat.format(date));
        return formattedDate;
    }


    public String getSize() {
        return String.valueOf(size);
    }

}
