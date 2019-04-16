package com.min.action;

import com.min.entity.NewsInfo;
import com.min.service.LoadEventService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoadEventAction {
    @Resource
    private LoadEventService loadEventService;
    private List<NewsInfo> news;
    private List<String> eventEntities;
    private String eventId;
    private JSONObject result;

    public List<NewsInfo> getNews() {
        return news;
    }

    public void setNews(List<NewsInfo> news) {
        this.news = news;
    }

    public List<String> getEventEntities() {
        return eventEntities;
    }

    public void setEventEntities(List<String> eventEntities) {
        this.eventEntities = eventEntities;
    }

    public String getEventId() {
        return eventId;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String execute(){
        List<NewsInfo> news=loadEventService.getWholeEvent(eventId);

        Object[] entitiesAndKeywords=loadEventService.getEventEntitiesAndKeywords(eventId);
        Map<String,Object> re=new HashMap<String,Object>();
        List<Object> newsList=new ArrayList<Object>();
        for(NewsInfo newsInfo:news){
            Map<String,String> newsOne=new HashMap<String, String>();
            newsOne.put("newsTitle",newsInfo.getTitle());
            newsOne.put("newsTime",newsInfo.getTime());
            newsList.add(newsOne);
        }
        re.put("news",newsList);
        re.put("entities",(ArrayList<ArrayList<Object>>)entitiesAndKeywords[0]);
        re.put("keywords",(ArrayList<ArrayList<Object>>)entitiesAndKeywords[1]);
        JSONObject jsonObject=JSONObject.fromObject(re);
        setResult(jsonObject);
        return "success";
    }
}
