package com.firefly.timeadd.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 增加天数
     * @param date
     * @return
     */
    public static Date addDay(Date date, int day){
        Calendar d1 = Calendar.getInstance();
        d1.setTime(date);
        d1.add(Calendar.DATE,day);
        return d1.getTime();
    }

    /**
     * 计算相差天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDayDiff(Date startDate,Date endDate){
        return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24));
    }

    /**
     * 设置为当天的零点
     * @param date
     * @return
     */
    public static Date getDayZero(Date date){
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        //时分秒置0
        d.set(Calendar.HOUR_OF_DAY,0);
        d.set(Calendar.MINUTE,0);
        d.set(Calendar.SECOND,0);
        return d.getTime();
    }

    public static Date getDayMax(Date date){
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        //时分秒置0
        d.set(Calendar.HOUR_OF_DAY,23);
        d.set(Calendar.MINUTE,59);
        d.set(Calendar.SECOND,59);
        return d.getTime();
    }

    /**
     * 合并日期和时间
     * @param date
     * @param time
     * @return
     */
    public static Date merge(Date date,Date time){
        Calendar d1 = Calendar.getInstance();
        d1.setTime(date);
        Calendar d2 = Calendar.getInstance();
        d2.setTime(time);

        d1.set(Calendar.HOUR_OF_DAY,d2.get(Calendar.HOUR_OF_DAY));
        d1.set(Calendar.MINUTE,d2.get(Calendar.MINUTE));
        d1.set(Calendar.SECOND,d2.get(Calendar.SECOND));

        return d1.getTime();
    }

    /**
     * 指定日期是否在星期数组中
     * @param date
     * @param weeks
     * @return
     */
    public static boolean isWeek(Date date,byte[] weeks){
        if(weeks!=null){
            Calendar d = Calendar.getInstance();
            d.setTime(date);
            d.setFirstDayOfWeek(Calendar.SUNDAY);//第一天为星期天
            int w=d.get(Calendar.DAY_OF_WEEK)-1;//由于返回的值是1-7，转为0-6
            for(byte week:weeks){
                if(w==week){
                    return true;
                }
            }
        }
        return false;
    }
}
