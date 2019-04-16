package com.min.service;

import com.min.action.LoadIndexAction;
import com.min.dao.NewsEventsEventlinesDao;
import com.min.dao.SearchEventLineDao;
import com.min.entity.EventInfo;
import com.min.entity.EventLineInfo;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;

@Service
public class LoadIndexService {
    @Resource
    private SearchEventLineDao searchEventLineDao;
    @Resource
    private EventLineInfo eventLineInfo;
    @Resource
    private NewsEventsEventlinesDao newsEventsEventlinesDao;

    public ArrayList<EventLineInfo> getRecentPassionEventLine(String startTime,String endTime,int limit){
        Calendar calendarNow=Calendar.getInstance();
        Calendar calendarLastMonth=Calendar.getInstance();
        calendarLastMonth.add(calendarNow.DATE,-30);
        String now=calendarNow.get(Calendar.YEAR)+"-"+calendarNow.get(Calendar.MONTH)+"-"+calendarNow.get(Calendar.HOUR_OF_DAY)+"T"+calendarNow.get(Calendar.HOUR)+":"+calendarNow.get(Calendar.MILLISECOND)+":00Z";
        String lastMonth=calendarLastMonth.get(Calendar.YEAR)+"-"+calendarLastMonth.get(Calendar.MONTH)+"-"+calendarLastMonth.get(Calendar.HOUR_OF_DAY)+"T"+calendarLastMonth.get(Calendar.HOUR)+":"+calendarLastMonth.get(Calendar.MILLISECOND)+":00Z";

        ArrayList<EventLineInfo> eventLineInfos=newsEventsEventlinesDao.getNewAndHotEventLine(startTime,endTime,limit);

//        Map<String,String> queryParamMap=new HashMap<String, String>();
//        queryParamMap.put("q",time);
//        queryParamMap.put("rows","100000");
//        queryParamMap.put("facet","true");
//        queryParamMap.put("facet.field","eventlines");
//        long start=System.currentTimeMillis();
//        ArrayList<EventLineInfo> eventLineInfos=searchEventLineDao.selectNewAndHotLine(queryParamMap,limit,time);
//        System.out.println("运行时间："+(System.currentTimeMillis()-start)+"ms");
//        System.out.println(eventLineInfos.size());
        System.out.println("start:"+startTime+"end:"+endTime);
        for(EventLineInfo eventLineInfo:eventLineInfos){
            System.out.println("eventline Id="+eventLineInfo.getTitle());
            System.out.println(eventLineInfo.getEventLineId());
            System.out.println("event count="+eventLineInfo.getEventList().size());
            for(EventInfo eventInfo:eventLineInfo.getEventList()){
                System.out.println(eventInfo.getEventId() +"          "+eventInfo.getStartTime());
            }
        }
        return eventLineInfos;

    }

}
