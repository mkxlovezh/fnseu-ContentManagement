package com.min.service;

import com.min.dao.NewsEventsEventlinesDao;
import com.min.dao.SearchEventLineDao;
import com.min.entity.EventInfo;
import com.min.entity.News;
import com.min.entity.NewsInfo;
import ognl.OgnlContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {
    @Resource
    private SearchEventLineDao searchEventLineDao;
    @Resource
    private NewsEventsEventlinesDao newsEventsEventlinesDao;
    public Map<String, Object> getKeywordsNews(String keywords,String page,String pageNum){
        Map<String,Object> keywordsNews=new HashMap<String,Object>();
        ArrayList<Object> allNews=new ArrayList<Object>();
        Map<String,Object> r=searchEventLineDao.searchNews(keywords,page,pageNum);
        ArrayList<NewsInfo> news= (ArrayList<NewsInfo>) r.get("newsInfos");
        String numfound=r.get("numfound").toString();
        for(NewsInfo newsInfo:news){
            Map<String,Object> oneNews=new HashMap<String, Object>();
            oneNews.put("newsTitle",newsInfo.getTitle());

            oneNews.put("newsTime",newsInfo.getTime());
            oneNews.put("newsEvent",newsEventsEventlinesDao.keywordsGetEvent(newsInfo.getId()));
            System.out.println(oneNews.get("newsTitle"));
            allNews.add(oneNews);
        }
        keywordsNews.put("news",allNews);
        keywordsNews.put("numfound",numfound);
        System.out.println(((HashMap<String,Object>)(((ArrayList<Object>)keywordsNews.get("news")).get(3))).get("newsTitle"));
        return keywordsNews;
    }
}
