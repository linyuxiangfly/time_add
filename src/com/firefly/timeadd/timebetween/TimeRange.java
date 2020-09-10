package com.firefly.timeadd.timebetween;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeRange {
    private Date startTime;
    private Date endTime;

    public TimeRange() {
    }

    public TimeRange(String startTime, String endTime) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        this.startTime = ft.parse("0000-00-00 "+startTime);
        this.endTime = ft.parse("0000-00-00 "+endTime);;
    }

    public TimeRange(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
