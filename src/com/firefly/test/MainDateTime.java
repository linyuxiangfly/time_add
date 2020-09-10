package com.firefly.test;

import com.firefly.timeadd.TimeAddUtil;
import com.firefly.timeadd.timebetween.DateBetween;
import com.firefly.timeadd.timebetween.DateTimeBetween;
import com.firefly.timeadd.timebetween.TimeRange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainDateTime {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

        List<DateTimeBetween> list=new ArrayList<>();

        List<TimeRange> timeRangeList=new ArrayList<>();
        timeRangeList.add(new TimeRange("00:00:00","15:59:59"));

        list.add(new DateTimeBetween(
                true,
                "2020-08-21",
                "2020-08-30",
                timeRangeList));

        timeRangeList=new ArrayList<>();
        timeRangeList.add(new TimeRange("14:00:00","20:00:00"));

        list.add(new DateTimeBetween(
                true,
                "2020-08-21",
                "2020-08-22",
                timeRangeList
                ));

        //合并时间
//        List<DateBetween> data=TimeAddUtil.addDateTimeBetween(list);
        List<DateBetween> data=TimeAddUtil.addDateTimeBetweenTimeOfDay(list,new Date());
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
