package com.firefly.test;

import com.firefly.timeadd.TimeAddUtil;
import com.firefly.timeadd.timebetween.DateBetween;
import com.firefly.timeadd.timebetween.DateTimeBetween;
import com.firefly.timeadd.timebetween.TimeRange;
import com.firefly.timeadd.timebetween.WeekTimeBetween;
import com.firefly.timeadd.utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainWeekTime {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

        List<WeekTimeBetween> list=new ArrayList<>();

        List<TimeRange> timeRangeList=new ArrayList<>();
        timeRangeList.add(new TimeRange("08:00:00","12:00:00"));

        list.add(new WeekTimeBetween(
                false,
                new byte[]{6,2,3},
                timeRangeList));

        timeRangeList=new ArrayList<>();
        timeRangeList.add(new TimeRange("07:00:00","13:00:00"));
        timeRangeList.add(new TimeRange("12:00:00","14:00:00"));
        timeRangeList.add(new TimeRange("16:00:00","23:00:00"));

        list.add(new WeekTimeBetween(
                true,
                new byte[]{6,2,4},
                timeRangeList
                ));

        //合并时间
//        List<DateBetween> data=TimeAddUtil.addWeekTimeBetween(list,new Date(), DateUtil.addDay(new Date(),1));
        List<DateBetween> data=TimeAddUtil.addWeekTimeBetweenTimeOfDay(list,new Date());
        System.out.println("组合日期时间################################");
        for(DateBetween d:data){
            System.out.println("allow:"+d.isAllow()+",start:"+ft.format(d.getStart())+",end:"+ft.format(d.getEnd()));
        }

        //合并时间
        data=TimeAddUtil.addDateBetween(data);
        System.out.println("合并时间段###################################");
        for(DateBetween d:data){
            System.out.println("allow:"+d.isAllow()+",start:"+ft.format(d.getStart())+",end:"+ft.format(d.getEnd()));
        }
    }
}
