package com.min.service;

import com.min.dao.NewsEventsEventlinesDao;
import com.min.dao.SearchEventLineDao;
import com.min.entity.EventInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class LoadEventLineService {

    @Resource
    private SearchEventLineDao searchEventLineDao;
    @Resource
    private NewsEventsEventlinesDao newsEventsEventlinesDao;
    public ArrayList<EventInfo> getWholeEventLine(String eventlineId){
        return newsEventsEventlinesDao.getEvents(eventlineId,"2017-12-01 00:00:00","2020-12-12 00:00:00");
    }
    public Object[] getEventlineEntitiesAndKeywords(String eventlineId){
        return newsEventsEventlinesDao.getEventLineEntitiesAndKeywords(eventlineId);
    }

}
