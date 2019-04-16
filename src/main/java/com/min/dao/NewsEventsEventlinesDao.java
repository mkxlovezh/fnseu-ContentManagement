package com.min.dao;

import com.min.entity.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Repository
public class NewsEventsEventlinesDao {
    @Resource
    private SessionFactory sessionFactory;



    public ArrayList<EventLineInfo> getNewAndHotEventLine(String startTime,String endTime,int limit){
        String sql="SELECT COUNT(*) AS num, eventline_id FROM news_events_eventlines WHERE TIME BETWEEN ?1 AND ?2 GROUP BY eventline_id  ORDER BY num DESC";
        List results=sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar("num", StandardBasicTypes.INTEGER).addScalar("eventline_id",StandardBasicTypes.STRING).setString("1",startTime).setString("2",endTime).list();
        ArrayList<EventLineInfo> eventLineInfos=new ArrayList<EventLineInfo>();
        int i=1;
        for(Object element:results){
            EventLineInfo eventLineInfo = new EventLineInfo();
            Object[] row=(Object[])element;
            String eventLineId=row[1].toString();
            eventLineInfo.setEventLineId(eventLineId);
            eventLineInfo.setPassionNow((Integer.parseInt(row[0].toString())));
            eventLineInfo.setTitle(getEventLineKeywordsAndPassion(eventLineId)[0].toString());
            eventLineInfo.setPassion(Integer.parseInt(getEventLineKeywordsAndPassion(eventLineId)[1].toString()));
            eventLineInfo.setTime(getEventLineStartTime(eventLineId));
            ArrayList<EventInfo> eventInfos=getEvents(eventLineId,startTime,endTime);
            eventLineInfo.setEventList(eventInfos);
            eventLineInfos.add(eventLineInfo);
            if(i>=limit){
                break;
            }
            i++;
        }
        return eventLineInfos;
    }
    public ArrayList<EventInfo> getEvents(String eventLineId,String startTime,String endTime){
        long start=System.currentTimeMillis();
        String sql="SELECT id,time,keywords,doc_num FROM events WHERE eventline=?1  AND time BETWEEN ?2 AND ?3 ORDER BY time ASC";
        List list=sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar("id",StandardBasicTypes.STRING).addScalar("time",StandardBasicTypes.STRING).addScalar("keywords",StandardBasicTypes.STRING).addScalar("doc_num",StandardBasicTypes.INTEGER).setString("1",eventLineId).setString("2",startTime).setString("3",endTime).list();
        System.out.println("执行"+(System.currentTimeMillis()-start));
        ArrayList<EventInfo> eventInfos=new ArrayList<EventInfo>();
        for(int i=0;i<list.size();i++) {
            Object[] elements=(Object[])list.get(i);
            String eventId=elements[0].toString();
            String staT=elements[1].toString();
            String endT=null;
            if(i!=list.size()-1){
                endT=((Object[])list.get(i+1))[1].toString();
            }
            else {
                endT="2018-12-30 23:59:59";
            }

            EventInfo eventInfo=new EventInfo();
            eventInfo.setEventId(eventId);
            eventInfo.setKeywords(getTitleFromKeywords(elements[2].toString()));
            eventInfo.setPassion(Integer.parseInt(elements[3].toString()));
            eventInfo.setStartTime(staT);
            eventInfo.setEndTime(endT);
            long s=System.currentTimeMillis();

            eventInfo.setTitle(getEventTitle(eventId));

            eventInfos.add(eventInfo);
            System.out.println("现在是"+(System.currentTimeMillis()-s));
        }
        System.out.println("现在时间是"+(System.currentTimeMillis()-start));

        return eventInfos;
    }
    public ArrayList<NewsInfo> getNews(String eventId){
        String sql="SELECT n.title title ,n.time time FROM news n , news_events ne WHERE n.id=ne.news_id AND ne.event_id=?1";
        ArrayList<NewsInfo> newsInfos=new ArrayList<NewsInfo>();
        List list=sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar("title",StandardBasicTypes.STRING).addScalar("time",StandardBasicTypes.STRING).setString("1",eventId).list();
        for(Object news :list){
            Object[] re=(Object[])news;
            NewsInfo newsInfo=new NewsInfo();
            newsInfo.setTitle(re[0].toString());
            newsInfo.setTime(re[1].toString());
            newsInfos.add(newsInfo);
        }
        return newsInfos;
    }
    public ArrayList<EventInfo> keywordsGetEvent(String newsId){
        String sql="SELECT event_id FROM news_events WHERE news_id=?1";
        ArrayList<EventInfo> eventInfos=new ArrayList<EventInfo>();
        List list=sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar("event_id",StandardBasicTypes.STRING).setString("1",newsId).list();
        for(Object event:list){

            EventInfo eventInfo=new EventInfo();
            eventInfo.setEventId(event.toString());
            eventInfo.setTitle(getEventTitle(event.toString()));
            eventInfos.add(eventInfo);
        }
        return eventInfos;
    }
    public String getEventTitle(String eventId){
        String sql="SELECT n.title title ,n.time time FROM news n , news_events ne WHERE n.id=ne.news_id AND ne.event_id=?1";
        ArrayList<NewsInfo> newsInfos=new ArrayList<NewsInfo>();
        List list=sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar("title",StandardBasicTypes.STRING).addScalar("time",StandardBasicTypes.STRING).setString("1",eventId).list();
        if(list.size()==1){
            return ((Object[])(list.get(0)))[0].toString();
        }else{
            return ((Object[])(list.get(list.size()/2)))[0].toString();
        }

    }
    public Object[] getEventLineKeywordsAndPassion(String eventLineId){
        Object[] res=new Object[2];
        String sql="SELECT keywords,doc_num FROM eventlines WHERE id =?1";
        List results=sessionFactory.getCurrentSession().createSQLQuery((sql)).addScalar("keywords",StandardBasicTypes.STRING).addScalar("doc_num",StandardBasicTypes.INTEGER).setString("1",eventLineId).list();
        res[0]=getTitleFromKeywords(((Object[])(results.get(0)))[0].toString());
        res[1]=((Object[])(results.get(0)))[1];
        return res;
    }

    public String getEventLineStartTime(String eventLineId){

        String sql="SELECT time FROM news_events_eventlines WHERE eventline_id= ?1 ORDER BY time ASC ";
        List results=sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar("time",StandardBasicTypes.STRING).setString("1",eventLineId).list();
        return results.get(0).toString();
    }
    public String getTitleFromKeywords(String str){
        try{
            String keywords="";

            JSONArray object= net.sf.json.JSONArray.fromObject(str);
            for(int i=0;i<5&&i<object.size();i++){
                String key=object.getJSONArray(i).get(0).toString();
                keywords+=(key+" ");
            }
            return keywords;
        }catch (JSONException e){
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }
    public Object[] getEventLineEntitiesAndKeywords(String eventLineId){
        String sql="SELECT entities,keywords FROM eventlines WHERE id=?1";
        List list=sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar("entities",StandardBasicTypes.STRING).addScalar("keywords",StandardBasicTypes.STRING).setString("1",eventLineId).list();
        Object[] results=(Object[])list.get(0);
        Object[] re=new Object[2];
        re[0]= getEntitiesFromEntities(results[0].toString());
        re[1]=getEntitiesFromEntities(results[1].toString());
        return re;
    }
    public Object[] getEventEntitiesAndKeywords(String eventId){
        String sql="SELECT entities,keywords FROM events WHERE id=?1";
        List list=sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar("entities",StandardBasicTypes.STRING).addScalar("keywords",StandardBasicTypes.STRING).setString("1",eventId).list();
        Object[] results=(Object[])list.get(0);
        Object[] re=new Object[2];
        re[0]= getEntitiesFromEntities(results[0].toString());
        re[1]=getEntitiesFromEntities(results[1].toString());
        return re;
    }
    public ArrayList<ArrayList<Object>> getEntitiesFromEntities(String str){
        ArrayList<ArrayList<Object>> entites=new ArrayList<ArrayList<Object>>();
        JSONArray object= net.sf.json.JSONArray.fromObject(str);
        for(int i=0;i<object.size();i++){
            ArrayList<Object> oneEntity=new ArrayList<Object>();
            String key=object.getJSONArray(i).get(0).toString();
            Float value=Float.parseFloat(object.getJSONArray(i).getJSONObject(1).get("tf-idf").toString());
            oneEntity.add(key);
            oneEntity.add(value);
            entites.add(oneEntity);
        }
//        String[] entities=getTitleFromKeywords(str).split(" ");
//        ArrayList<String> entity=new ArrayList<String>();
//        for(int i=0;i<5&&i<entities.length;i++){
//            entity.add(entities[i]);
//        }
        return entites;
    }
    public static  void main(String [] args){
        String newsId="00036cb6-db91-11e8-b636-ecb1d755b669";
        NewsEventsEventlinesDao newsEventsEventlinesDao=new NewsEventsEventlinesDao();
        newsEventsEventlinesDao.keywordsGetEvent(newsId);

    }
}
