package com.firefly.timeadd.timebetween;

import java.util.Date;

/**
 * 时间段，如2020/8/21 10:00至2020/8/22 10:00
 */
public class DateBetween implements PassBetween {
    private boolean allow;
    private TimeBetweenType type=TimeBetweenType.date_between;
    private Date start;
    private Date end;

    public DateBetween() {
    }

    public DateBetween(boolean allow, Date start, Date end) {
        this.allow = allow;
        this.start = start;
        this.end = end;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
