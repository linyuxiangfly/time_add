package com.firefly.test;

import com.firefly.timeadd.TimeAddUtil;
import com.firefly.timeadd.timebetween.DateBetween;
import com.firefly.timeadd.timebetween.DateTimeBetween;
import com.firefly.timeadd.timebetween.PassBetween;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainTimeAdd {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

        List<DateBetween> list=new ArrayList<>();
//        list.add(new DateBetween(false, ft.parse("2020-08-20 00:00:00"),ft.parse("2020-08-20 10:00:00")));
////        list.add(new DateBetween(false, ft.parse("2020-08-22 00:00:00"),ft.parse("2020-08-23 23:59:59")));
//        list.add(new DateBetween(false, ft.parse("2020-08-21 23:59:59"),ft.parse("2020-08-22 00:00:00")));
//        list.add(new DateBetween(false, ft.parse("2020-08-21 00:00:00"),ft.parse("2020-08-21 23:59:59")));
//
////        list.add(new DateBetween(false, ft.parse("2020-08-19 12:00:00"),ft.parse("2020-08-20 12:00:00")));
////        list.add(new DateBetween(false, ft.parse("2020-08-20 10:00:00"),ft.parse("2020-08-21 13:00:00")));
//
//        list.add(new DateBetween(true, ft.parse("2020-08-22 08:00:00"),ft.parse("2020-08-23 10:00:00")));
//        list.add(new DateBetween(true, ft.parse("2020-08-22 07:00:00"),ft.parse("2020-08-22 09:00:00")));
//        list.add(new DateBetween(true, ft.parse("2020-08-20 00:00:00"),ft.parse("2020-08-21 23:59:59")));
//        list.add(new DateBetween(true, ft.parse("2020-08-19 00:00:00"),ft.parse("2020-08-20 23:59:59")));
//        list.add(new DateBetween(true, ft.parse("2020-08-21 08:00:00"),ft.parse("2020-08-22 07:00:00")));

        list.add(new DateBetween(true, ft.parse("2020-08-21 08:00:00"),ft.parse("2020-08-23 10:00:00")));
        list.add(new DateBetween(false, ft.parse("2020-08-21 09:00:00"),ft.parse("2020-08-23 09:00:00")));

        //合并时间
//        List<DateBetween> data=TimeAddUtil.addDateBetween(list);
        List<DateBetween> data=TimeAddUtil.addDateBetweenTimeOfDay(list,new Date());
        for(DateBetween d:data){
            System.out.println("allow:"+d.isAllow()+",start:"+ft.format(d.getStart())+",end:"+ft.format(d.getEnd()));
        }
    }
}
