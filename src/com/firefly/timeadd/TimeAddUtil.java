package com.firefly.timeadd;

import com.firefly.timeadd.timebetween.*;
import com.firefly.timeadd.utils.DateUtil;

import java.util.*;

public class TimeAddUtil {
    /**
     * 组合,时间段，如2020/8/21 10:00至2020/8/22 10:00
     * @param passList
     * @return
     */
    public static List<DateBetween> addDateBetweenTimeOfDay(List<DateBetween> passList, Date date){
        List<DateBetween> ret=new ArrayList<>();
        List<DateBetween> datas=addDateBetween(passList);
        for(DateBetween data:datas){
            date=DateUtil.getDayZero(date);
            //判断指定日期是否在开始和结束日期内

            int dayDiffStart=DateUtil.getDayDiff(DateUtil.getDayZero(data.getStart()),date);
            int dayDiffEnd=DateUtil.getDayDiff(DateUtil.getDayZero(data.getEnd()),date);

            if(dayDiffStart>=0 && dayDiffEnd<=0){
//                ret.add(data);
                //全天
                DateBetween db=new DateBetween(data.isAllow(),DateUtil.getDayZero(date),DateUtil.getDayMax(date));
                //开
                if(dayDiffStart==0){
                    db.setStart((Date)data.getStart().clone());
                }
                if(dayDiffEnd==0){
                    db.setEnd((Date)data.getEnd().clone());
                }
                ret.add(db);
            }
        }
        datas.clear();
        return ret;
    }

    /**
     * 组合,时间段，如2020/8/21 10:00至2020/8/22 10:00
     * @param passList
     * @return
     */
    public static List<DateBetween> addDateBetween(List<DateBetween> passList){
        //将允许跟拒绝分开
        Map<Boolean,List> map=split(passList);

        //合并允许的时间
        List<DateBetween> trueData=map.get(true);
        if(trueData!=null){
            unionDateBetween(trueData);
        }

        //合并禁止的时间
        List<DateBetween> falseData=map.get(false);
        if(falseData!=null){
            unionDateBetween(falseData);
        }

        map.clear();

        //循环禁止时间
        if(trueData!=null && falseData!=null){
            for(DateBetween d:falseData){
                subDateBetween(trueData,d);
            }
            falseData.clear();
        }

        if(trueData!=null && trueData.size()==0 && falseData!=null){
            return falseData;
        }else if(trueData==null && falseData!=null){
            return falseData;
        }
        return trueData;
    }

    /**
     * 组合相同类型的时间段
     * @param passList
     */
    private static void unionDateBetween(List<DateBetween> passList)
    {
        for (int i=0; i<passList.size()-1;i++)
        {
            DateBetween di=passList.get(i);
            if(di!=null){
                for(int j=i+1;j<passList.size();j++){
                    DateBetween dj=passList.get(j);

                    if(dj!=null){
                        //如果是两段有交集
                        if(isMixed(di,dj)){
                            //如果dj的开始时间在di的后面
                            if (dj.getStart().after(di.getStart()))
                            {
                                //将的di的开始时间放到dj中
                                dj.setStart(di.getStart());
                            }
                            //如果dj的结束时间在di的前面
                            if(dj.getEnd().before(di.getEnd())){
                                //将的di的开始时间放到dj中
                                dj.setEnd(di.getEnd());
                            }

                            //上面将最大范围的开始结束时间放到dj，所以di可以删除了
                            passList.set(i,null);
                            di=dj;
                        }
                    }
                }
            }
        }

        for (int i = passList.size()-1; i >= 0;i--){
            if(passList.get(i)==null){
                passList.remove(i);
            }
        }
    }

    /**
     * 从一个允许时间列表中减去禁止的时间
     * @param passList
     * @param sub
     */
    private static void subDateBetween(List<DateBetween> passList,DateBetween sub){
        for (int i=0; i<passList.size();i++){
            DateBetween di=passList.get(i);

            //如果有交集
            if(isMixed(di,sub)){
                //时间完全相同
                if(di.getStart().getTime()==sub.getStart().getTime() && di.getEnd().getTime()==sub.getEnd().getTime()){
                    passList.remove(di);
                    i--;
                }
                //如果禁止在允许中间
                else if(!di.getStart().after(sub.getStart()) && !di.getEnd().before(sub.getEnd())){
                    //增加一个时间段
                    passList.add(new DateBetween(di.isAllow(),sub.getEnd(),(Date)di.getEnd().clone()));

                    //修改前一个时间段的结束时间
                    di.setEnd(sub.getStart());

                    if(di.getStart().getTime()==di.getEnd().getTime()){
                        passList.remove(di);
                    }else{
                        //因为增加了一个时间段，所以加1
                        i++;
                    }
                }
                //如果禁止盖过或者等于允许时间，都为禁止
                else if(!sub.getStart().after(di.getStart()) && !sub.getEnd().before(di.getEnd())){
                    passList.remove(i);
                    i--;
                }
                //如果禁止在允许的两边
                else{
                    //禁止时间段在允许时间的左边
                    if (di.getEnd().after(sub.getEnd()))
                    {
                        //将的di的开始时间设置为禁止的最后时间
                        di.setStart(sub.getEnd());
                    }
                    //禁止时间段在允许时间的右边
                    if(di.getEnd().before(sub.getEnd())){
                        //将的di的结束时间设置为禁止的开始时间
                        di.setEnd(sub.getStart());
                    }
                }
            }
        }
    }

    /**
     * 是否有交集
     */
    private static boolean isMixed(DateBetween d1,DateBetween d2){
        //d1的开始时间比d2结束时间大，或者d2的开始时间比d1结束时间大，说明它们没有交集
        if(d1.getStart().after(d2.getEnd()) || d2.getStart().after(d1.getEnd())){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 组合,时间段，如2020/8/21 至2020/8/22 的 10:00至11:00
     * @param passList
     * @return
     */
    public static List<DateBetween> addDateTimeBetweenTimeOfDay(List<DateTimeBetween> passList, Date date){
        List<DateBetween> ret=new ArrayList<>();

        for (DateTimeBetween dtb:passList){
            if(dtb.getTimeRanges()!=null){
                //置为零点
                Date startDate=DateUtil.getDayZero(dtb.getStartDate());
                Date endDate=DateUtil.getDayZero(dtb.getEndDate());

                if(date!=null){
                    date=DateUtil.getDayZero(date);
                    //判断指定日期是否在开始和结束日期内
                    if(DateUtil.getDayDiff(startDate,date)>=0 && DateUtil.getDayDiff(endDate,date)<=0){
                        startDate=date;
                        endDate=date;
                    }else{
                        continue;
                    }
                }

                //相差天数
                int dayDiff= DateUtil.getDayDiff(startDate,endDate)+1;

                //循环开始日期到结束日期
                for(int i=0;i<dayDiff;i++){
                    Date day=DateUtil.addDay(startDate,i);

                    //循环多个时间段
                    for(TimeRange tr:dtb.getTimeRanges()){
                        DateBetween db=new DateBetween(
                                dtb.isAllow(),
                                DateUtil.merge(day,tr.getStartTime()),
                                DateUtil.merge(day,tr.getEndTime()));
                        ret.add(db);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 组合,时间段，如2020/8/21 至2020/8/22 的 10:00至11:00
     * @param passList
     * @return
     */
    public static List<DateBetween> addDateTimeBetween(List<DateTimeBetween> passList){
        return addDateTimeBetweenTimeOfDay(passList,null);
    }



    /**
     * 组合,星期+时间段，如星期1，星期2 的  10:00至11:00
     * @param passList
     * @return
     */
    public static List<DateBetween> addWeekTimeBetweenTimeOfDay(List<WeekTimeBetween> passList, Date date){
        return addWeekTimeBetween(passList,date,date);
    }

    /**
     * 组合,星期+时间段，如星期1，星期2 的  10:00至11:00
     * @param passList
     * @return
     */
    public static List<DateBetween> addWeekTimeBetween(List<WeekTimeBetween> passList,Date startDate,Date endDate){
        List<DateBetween> ret=new ArrayList<>();

        //置为零点
        startDate=DateUtil.getDayZero(startDate);
        endDate=DateUtil.getDayZero(endDate);
        //相差天数
        int dayDiff= DateUtil.getDayDiff(startDate,endDate)+1;

        for(WeekTimeBetween wtb:passList){
            if(wtb.getWeeks()!=null && wtb.getTimeRanges()!=null){
                //循环开始日期到结束日期
                for(int i=0;i<dayDiff;i++){
                    Date day=DateUtil.addDay(startDate,i);
                    //判断指定日期是否在星期数组中
                    if(DateUtil.isWeek(day,wtb.getWeeks())){
                        //循环多个时间段
                        for(TimeRange tr:wtb.getTimeRanges()){
                            DateBetween db=new DateBetween(
                                    wtb.isAllow(),
                                    DateUtil.merge(day,tr.getStartTime()),
                                    DateUtil.merge(day,tr.getEndTime()));
                            ret.add(db);
                        }
                    }
                }
            }
        }

        return ret;
    }

    private static Map<Boolean,List> split(List<? extends PassBetween> passList){
        Map<Boolean,List> ret=new HashMap<>();

        for(PassBetween passBetween:passList){
            List list=ret.get(passBetween.isAllow());

            if(list==null){
                list=new ArrayList<>();
                ret.put(passBetween.isAllow(),list);
            }

            list.add(passBetween);
        }

        return ret;
    }

    /**
     * 允许的排前面，禁止的排后面
     * @param passList
     */
    private static void sort(List<? extends PassBetween> passList){
        //通过集合提供的方法实现
        Collections.sort(passList, new Comparator<PassBetween>() {

            @Override
            public int compare(PassBetween o1, PassBetween o2) {
                if(o1.isAllow()==o2.isAllow()){
                    return 0;
                }else if(o1.isAllow() && !o2.isAllow()){
                    return -1;
                }else if(!o1.isAllow() && o2.isAllow()){
                    return 1;
                }
                return 0;
            }
        });
    }
}
