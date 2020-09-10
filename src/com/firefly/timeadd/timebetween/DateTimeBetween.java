package com.firefly.timeadd.timebetween;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 时间段，如2020/8/21 至2020/8/22 的 10:00至11:00
 */
public class DateTimeBetween implements PassBetween {
    private boolean allow;
    private TimeBetweenType type=TimeBetweenType.date_time_between;
    private Date startDate;
    private Date endDate;
    private List<TimeRange> timeRanges;

    public DateTimeBetween() {

    }

    public DateTimeBetween(boolean allow, String startDate, String endDate, List<TimeRange> timeRanges) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        this.allow = allow;
        this.startDate = ft.parse(startDate+" 00:00:00");
        this.endDate = ft.parse(endDate+" 00:00:00");
        this.timeRanges=timeRanges;
    }

    public DateTimeBetween(boolean allow, Date startDate, Date endDate, List<TimeRange> timeRanges) {
        this.allow = allow;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeRanges=timeRanges;
    }

    @Override
    public TimeBetweenType getType() {
        return type;
    }

    @Override
    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<TimeRange> getTimeRanges() {
        return timeRanges;
    }

    public void setTimeRanges(List<TimeRange> timeRanges) {
        this.timeRanges = timeRanges;
    }

}
