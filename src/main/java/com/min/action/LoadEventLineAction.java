package com.min.action;

import com.min.entity.EventInfo;
import com.min.service.LoadEventLineService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoadEventLineAction  {
    @Resource
    private LoadEventLineService loadEventLineService;

    private List<EventInfo> eventInfoList;

    private List<String> eventlineEntities;

    private String eventlineId;

    private JSONObject result;

    public List<EventInfo> getEventInfoList() {
        return eventInfoList;
    }

    public void setEventInfoList(List<EventInfo> eventInfoList) {
        this.eventInfoList = eventInfoList;
    }

    public List<String> getEventlineEntities() {
        return eventlineEntities;
    }

    public void setEventlineEntities(List<String> eventlineEntities) {
        this.eventlineEntities = eventlineEntities;
    }

    public String getEventlineId() {
        return eventlineId;
    }

    public void setEventlineId(String eventlineId) {
        this.eventlineId = eventlineId;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public String execute(){
        System.out.println(eventlineId);
        List<EventInfo> events=loadEventLineService.getWholeEventLine(eventlineId);
        Object[] r=loadEventLineService.getEventlineEntitiesAndKeywords(eventlineId);

        Map<String,Object> re=new HashMap<String,Object>();
        List<Object> eventList=new ArrayList<Object>();
        for(EventInfo eventInfo:events){
            Map<String,Object> eventOne=new HashMap<String, Object>();
            eventOne.put("eventId",eventInfo.getEventId());
            eventOne.put("eventTitle",eventInfo.getTitle());
            eventOne.put("eventKeywords",eventInfo.getKeywords());
            eventOne.put("eventStartTime",eventInfo.getStartTime());
            eventOne.put("eventEndTime",eventInfo.getEndTime());
            eventOne.put("eventPassion",eventInfo.getPassion());
            eventList.add(eventOne);
        }
        re.put("events",eventList);
        re.put("entities",(ArrayList<ArrayList<Object>>)r[0]);
        re.put("keywords",(ArrayList<ArrayList<Object>>)r[1]);
        JSONObject json=JSONObject.fromObject(re);
        setResult(json);
        return "success";
    }
}
