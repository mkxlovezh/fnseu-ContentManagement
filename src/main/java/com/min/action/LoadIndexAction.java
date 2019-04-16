package com.min.action;


import com.min.entity.EventInfo;
import com.min.entity.EventLineInfo;
import com.min.entity.NewsInfo;
import com.min.service.LoadIndexService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoadIndexAction {
    @Resource
    private LoadIndexService loadIndexService;
    //private List<EventLineInfo>  eventLines;
    private String startTime;
    private String endTime;
    private int limit=5;
    private JSONObject result;

//    public List<EventLineInfo> getEventLines() {
//        return eventLines;
//    }
//
//    public void setEventLines(List<EventLineInfo> eventLines) {
//        this.eventLines = eventLines;
//    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public String execute(){

        System.out.println(limit);
        ArrayList<EventLineInfo> eventLineInfos=loadIndexService.getRecentPassionEventLine(startTime,endTime,limit);
        Map<String,Object> eventlines=new HashMap<String, Object>();
        List<Object> eventlineList=new ArrayList<Object>();
        for(EventLineInfo eventLineInfo:eventLineInfos){
            Map<String,Object> oneEventline=new HashMap<String ,Object>();
            oneEventline.put("eventLineId",eventLineInfo.getEventLineId());
            oneEventline.put("eventlineTitle",eventLineInfo.getTitle());
            oneEventline.put("eventLineStartTime",eventLineInfo.getTime());
            oneEventline.put("passion",eventLineInfo.getPassion());
            oneEventline.put("passionNow",eventLineInfo.getPassionNow());
            List<Object> eventList=new ArrayList<Object>();
            for(EventInfo eventInfo:eventLineInfo.getEventList()){
                Map<String,Object> oneEvent=new HashMap<String,Object>();
                oneEvent.put("eventId",eventInfo.getEventId());
                oneEvent.put("eventTitle",eventInfo.getTitle());
                oneEvent.put("eventStartTime",eventInfo.getStartTime());
                oneEvent.put("passion",eventInfo.getPassion());
                eventList.add(oneEvent);
            }
            oneEventline.put("eventLineEvents",eventList);
            eventlineList.add(oneEventline);
        }
        eventlines.put("eventLines",eventlineList);
        JSONObject json=JSONObject.fromObject(eventlines);
        setResult(json);
        System.out.println("success");
        return "success";
    }
}
