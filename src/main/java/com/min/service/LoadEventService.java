package com.min.service;

import com.min.dao.NewsEventsEventlinesDao;
import com.min.dao.SearchEventLineDao;
import com.min.entity.NewsEventsEventlines;
import com.min.entity.NewsInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoadEventService {
    @Resource
    private SearchEventLineDao searchEventLineDao;
    @Resource
    private NewsEventsEventlinesDao newsEventsEventlinesDao;
    public ArrayList<NewsInfo> getWholeEvent(String eventId){
        return newsEventsEventlinesDao.getNews(eventId);
    }
    public Object[] getEventEntitiesAndKeywords(String eventId){
        return newsEventsEventlinesDao.getEventEntitiesAndKeywords(eventId);
    }
}
