package com.firefly.timeadd.timebetween;

import java.util.Date;
import java.util.List;

/**
 * 星期+时间段，如星期1，星期2 的  10:00至11:00
 */
public class WeekTimeBetween implements PassBetween {
    private boolean allow;
    private TimeBetweenType type=TimeBetweenType.week_time_between;
    private byte[] weeks;
    private List<TimeRange> timeRanges;

    public WeekTimeBetween() {
    }

    public WeekTimeBetween(boolean allow, byte[] weeks, List<TimeRange> timeRanges) {
        this.allow = allow;
        this.weeks = weeks;
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

    public byte[] getWeeks() {
        return weeks;
    }

    public void setWeeks(byte[] weeks) {
        this.weeks = weeks;
    }

    public List<TimeRange> getTimeRanges() {
        return timeRanges;
    }

    public void setTimeRanges(List<TimeRange> timeRanges) {
        this.timeRanges = timeRanges;
    }
}
