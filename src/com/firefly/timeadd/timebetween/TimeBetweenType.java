package com.firefly.timeadd.timebetween;

/**
 * 时间范围类型
 */
public enum TimeBetweenType {
    date_between,//时间段，如2020/8/21 10:00至2020/8/22 10:00
    date_time_between,//时间段，如2020/8/21 至2020/8/22 的 10:00至11:00
    week_time_between,//星期+时间段，如星期1，星期2 的  10:00至11:00
}
