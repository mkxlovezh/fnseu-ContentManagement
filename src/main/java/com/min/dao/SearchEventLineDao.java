package com.min.dao;

import com.min.entity.EventInfo;
import com.min.entity.EventLineInfo;
import com.min.entity.NewsInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;



import org.springframework.stereotype.Repository;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.neo4j.helpers.Args.parse;

@Repository
public class SearchEventLineDao {
    @Resource
    HttpSolrClient httpSolrClientNews;
    @Resource
    HttpSolrClient httpSolrClientEventLines;
    @Resource
    HttpSolrClient httpSolrClientEvents;



    /**
     * 获取事件线列表
     * @param
     * @param
     * @return
     */
//    public ArrayList<EventLineInfo> selectNewAndHotLine(Map<String,String> queryParamMap,int listLen,String time){
//        ArrayList<EventLineInfo> eventLineInfos=new ArrayList<EventLineInfo>();
//        MapSolrParams queryparams=new MapSolrParams(queryParamMap);
//        try {
//            QueryResponse response=httpSolrClientNews.query(queryparams);
//            List<FacetField> facets= response.getFacetFields();
//
//            for(FacetField facetField:facets){
//
//                List<FacetField.Count> counts=facetField.getValues();
//                int i=0;
//                for(FacetField.Count count:counts){
//                    if(i<listLen) {
//                        EventLineInfo eventLineInfo = new EventLineInfo();
//                        //事件线包含事件线id、事件线当前热度、事件线总热度、事件线标题、事件线开始时间
//                        eventLineInfo.setEventLineId(count.getName());
//                        System.out.println("eventLine:"+count.getName());
//                        eventLineInfo.setPassionNow((int) count.getCount());
//                        eventLineInfo.setTitle(getTitleFromKeywords(eventLineKeywords(count.getName())[0]));
//                        eventLineInfo.setPassion(Integer.parseInt(eventLineKeywords(count.getName())[1]));
//                        eventLineInfo.setTime(eventLineStartTime(count.getName()));
//                        String queryParamMap1;  //搜索某事件线的指定时间段的事件
//                        queryParamMap1=time;
//
//                        ArrayList<EventInfo> eventInfos=searchEventLineEvent(count.getName(),"eventlines:"+count.getName()+" AND "+queryParamMap1);
//                        Collections.sort(eventInfos, new Comparator<EventInfo>() {
//                            @Override
//                            public int compare(EventInfo o1, EventInfo o2) {
//                                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                                try {
//                                    String da1=o1.getStartTime();
//                                    String da2=o2.getStartTime();
//                                    Date dt1 = format.parse(da1);
//                                    Date dt2 = format.parse(da2);
//                                    if (dt1.getTime() > dt2.getTime()) {
//                                        return 1;
//                                    } else if (dt1.getTime() < dt2.getTime()) {
//                                        return -1;
//                                    } else {
//                                        return 0;
//                                    }
//                                }catch (Exception e){
//
//                                   e.printStackTrace();
//                                }
//                                return 0;
//                            }
//                        });
//
//                        eventLineInfo.setEventList(eventInfos);
//                        eventLineInfos.add(eventLineInfo);
//                        i++;
//                    }
//                }
//
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return eventLineInfos;
//    }
//    public ArrayList<EventInfo> searchEventLineEvent(String eventLineId ,String querySentence){
//        SolrQuery solrQuery=new SolrQuery(querySentence);
//        solrQuery.setRows(10000);
//        ArrayList<EventInfo> eventInfos=new ArrayList<EventInfo>();
//        try{
//            QueryResponse response=httpSolrClientEvents.query(solrQuery);
//            SolrDocumentList solrDocuments=response.getResults();
//
//            for(SolrDocument solrDocument:solrDocuments){
//                EventInfo eventInfo=new EventInfo();
//                String eventId=solrDocument.getFieldValue("id").toString();
//                String keywords=getTitleFromKeywords(solrDocument.getFieldValue("keywords").toString());
//                eventInfo.setEventId(eventId);
//                eventInfo.setPassion((Integer) solrDocument.getFieldValue("doc_num"));
//                eventInfo.setStartTime(timeConversation(solrDocument.getFieldValue("time").toString()));
//                eventInfo.setKeywords(keywords);
//                eventInfo.setEndTime(eventStartTimeAndEndTime(eventLineId, eventId)[1]);
//                ArrayList<NewsInfo> newsList = searchEventNews(eventId);
//                eventInfo.setTitle(newsList.get(0).getTitle());
//                eventInfo.setNewsList(newsList);
//                eventInfos.add(eventInfo);
//            }
//            return eventInfos;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//    /**
//     * 根据事件线id 搜索事件列表
//     * @param eventLineId
//     * @param querySentence
//     * @return
//     */

//    public ArrayList<EventInfo> searchEventLineEvent(String eventLineId,String querySentence){
//        SolrQuery solrQuery=new SolrQuery(querySentence);
//        solrQuery.setFacet(true);
//        solrQuery.addFacetField("events");
//        solrQuery.setFacetMinCount(1);
//        solrQuery.setFacetLimit(-1);
//        Set<String> events=getSingleEvent(eventLineId);
//        solrQuery.setRows(10000);
//        ArrayList<EventInfo> eventInfos=new ArrayList<EventInfo>();
//        long start=System.currentTimeMillis();
//        try {
//            QueryResponse response = httpSolrClientNews.query(solrQuery);
//            List<FacetField> facets = response.getFacetFields();
//            for (FacetField facetField : facets) {
//                List<FacetField.Count> counts = facetField.getValues();
//                for (FacetField.Count count : counts) {
//                        EventInfo eventInfo = new EventInfo();
//                        //事件包含事件的Id、事件的热度、事件的开始时间及结束时间、事件的标题
//                        if(events.contains(count.getName())) {
//                            eventInfo.setEventId(count.getName());
//                            eventInfo.setPassion((int) count.getCount());
//                            eventInfo.setStartTime(eventStartTimeAndEndTime(eventLineId, count.getName())[0]);
//                            eventInfo.setEndTime(eventStartTimeAndEndTime(eventLineId, count.getName())[1]);
//                            ArrayList<NewsInfo> newsList = searchEventNews(count.getName());
//                            eventInfo.setKeywords(getTitleFromKeywords(eventKeywords(count.getName())));
//                            eventInfo.setTitle(newsList.get(0).getTitle().replaceAll("\""," "));
//                            eventInfo.setNewsList(newsList);
//                            eventInfos.add(eventInfo);
//                        }
//                }
//            }
//            System.out.println("运行时间："+(System.currentTimeMillis()-start)+"ms");
//            return eventInfos;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
    public Map<String, Object> searchNews(String keywords,String page,String pageNum){
        SolrQuery query=new SolrQuery("content:"+keywords);
        int rows=Integer.parseInt(pageNum);
        query.setRows(rows);
        query.setStart((Integer.parseInt(page)-1)*rows);
        ArrayList<NewsInfo> newsInfos=new ArrayList<NewsInfo>();
        try{
            QueryResponse queryResponse=httpSolrClientNews.query(query);
            SolrDocumentList solrDocuments=queryResponse.getResults();
            Map<String,Object> r=new HashMap<String,Object>();
            Long numfound=solrDocuments.getNumFound();
            r.put("numfound",numfound);
            for(SolrDocument solrDocument:solrDocuments){
                NewsInfo newsInfo=new NewsInfo();
                newsInfo.setTitle(solrDocument.getFieldValue("title").toString());
                newsInfo.setTime(timeConversation(solrDocument.getFieldValue("time").toString()));
                newsInfo.setId(solrDocument.getFieldValue("id").toString());
                newsInfos.add(newsInfo);
            }
            r.put("newsInfos",newsInfos);
            return r;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取指定事件线和事件id的关联新闻
     * @param
     * @return
     */
//    public ArrayList<NewsInfo> searchEventNews(String eventId){
//        SolrQuery query=new SolrQuery("events:"+eventId);
//        ArrayList<NewsInfo> newsInfos=new ArrayList<NewsInfo>();
//        try {
//            QueryResponse queryResponse=httpSolrClientNews.query(query);
//            SolrDocumentList solrDocuments= queryResponse.getResults();
//            for(SolrDocument solrDocument:solrDocuments){
//                NewsInfo newsInfo=new NewsInfo();
//                newsInfo.setTitle(solrDocument.getFieldValue("title").toString());
//                newsInfo.setTime(timeConversation(solrDocument.getFieldValue("time").toString()));
////                newsInfo.setUrl(solrDocument.getFieldValue("url").toString());
////                newsInfo.setKeywords(solrDocument.getFieldValue("keywords").toString());
////                newsInfo.setAbstr(solrDocument.getFieldValue("abstact").toString());
//                newsInfos.add(newsInfo);
//            }
//            return newsInfos;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 获取指定事件id的起始时间和结束时间
//     * @param eventLineId
//     * @param eventId
//     * @return
//     */
//    public String[] eventStartTimeAndEndTime(String eventLineId,String eventId){
//        String[] time=new String[2];
//        SolrQuery query = new SolrQuery("eventlines:"+eventLineId+"AND events:"+eventId);
//        query.setFields("time");
//        query.addSort(new SolrQuery.SortClause("time", SolrQuery.ORDER.asc));
//        try {
//            QueryResponse queryResponse=httpSolrClientNews.query(query);
//            SolrDocumentList solrDocuments= queryResponse.getResults();
//            time[0]=timeConversation(solrDocuments.get(0).getFieldValue("time").toString());
//            time[1]=timeConversation(solrDocuments.get(solrDocuments.size()-1).getFieldValue("time").toString());
//            return time;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 获取指定事件线的开始时间
//     * @param eventLineId
//     * @return
//     */
//    public String eventLineStartTime(String eventLineId){
//
//        SolrQuery query = new SolrQuery("eventlines:"+eventLineId);
//        query.setFields("time");
//        query.addSort(new SolrQuery.SortClause("time", SolrQuery.ORDER.asc));
//        try {
//            QueryResponse queryResponse=httpSolrClientNews.query(query);
//            SolrDocumentList solrDocuments= queryResponse.getResults();
//
//            return timeConversation(solrDocuments.get(0).getFieldValue("time").toString());
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 获取指定事件线的关键词
//     * @param eventLineId
//     * @return
//     */
//    public String[] eventLineKeywords(String eventLineId){
//        String[] results=new String[2];
//        SolrQuery solrQuery=new SolrQuery("id:"+eventLineId);
//        try {
//            QueryResponse queryResponse=httpSolrClientEventLines.query(solrQuery);
//            SolrDocumentList solrDocuments= queryResponse.getResults();
//            results[0]=solrDocuments.get(0).getFieldValue("keywords").toString();
//            results[1]=solrDocuments.get(0).getFieldValue("doc_num").toString();
//
//            return results;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 获取指定事件的关键词
//     * @param eventId
//     * @return
//     */
//    public String eventKeywords(String eventId){
//        SolrQuery solrQuery=new SolrQuery("id:"+eventId);
//        try{
//
//            QueryResponse queryResponse=httpSolrClientEvents.query(solrQuery);
//            SolrDocumentList solrDocuments=queryResponse.getResults();
//            return solrDocuments.get(0).getFieldValue("keywords").toString();
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public Set<String> getSingleEvent(String eventLineId){
//        Set<String> events=new HashSet<String>();
//        SolrQuery solrQuery=new SolrQuery("eventlines:"+eventLineId);
//        solrQuery.setRows(1000);
//        try {
//            QueryResponse queryResponse=httpSolrClientEvents.query(solrQuery);
//            SolrDocumentList solrDocuments= queryResponse.getResults();
//            for(SolrDocument solrDocument:solrDocuments){
//                events.add(solrDocument.getFieldValue("id").toString());
//            }
//            return events;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public ArrayList<String> getEventLineEntites(String eventLineId){
//
//        SolrQuery solrQuery=new SolrQuery("id:"+eventLineId);
//        try {
//            QueryResponse queryResponse=httpSolrClientEventLines.query(solrQuery);
//            SolrDocumentList solrDocuments= queryResponse.getResults();
//            String entities= solrDocuments.get(0).getFieldValue("entities").toString();
//            System.out.println(entities);
//            return getEntitiesFromEntities(entities);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public ArrayList<String> getEventEntites(String eventId){
//
//        SolrQuery solrQuery=new SolrQuery("id:"+eventId);
//        try {
//            QueryResponse queryResponse=httpSolrClientEvents.query(solrQuery);
//            SolrDocumentList solrDocuments= queryResponse.getResults();
//            String entities= solrDocuments.get(0).getFieldValue("entities").toString();
//            return getEntitiesFromEntities(entities);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//
//    public String getTitleFromKeywords(String str){
//
//        try{
//            String keywords="";
//            JSONArray  object= JSONArray.fromObject(str);
//            for(int i=0;i<5&&i<object.size();i++){
//                String key=object.getJSONArray(i).get(0).toString();
//                keywords+=(key+" ");
//            }
//
//            return keywords;
//        }catch (JSONException e){
//            e.printStackTrace();
//            System.out.println("error");
//            return null;
//        }
//
//
//
//    }
//
//    public ArrayList<String> getEntitiesFromEntities(String str){
//
//        String[] entities=getTitleFromKeywords(str).split(" ");
//        ArrayList<String> entity=new ArrayList<String>();
//        for(int i=0;i<5&&i<entities.length;i++){
//            entity.add(entities[i]);
//        }
//        return entity;
//    }
//
//
    public String timeConversation(String time){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String da1=time;

            Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK).parse(da1);

            SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDate1=sdf1.format(date1);
            return sDate1;
        }catch (Exception e){

            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        String[] results=new String[1];
        Map<String, String> queryParamMap = new HashMap<String, String>();
        SearchEventLineDao searchEventLineDao = new SearchEventLineDao();
        final String solrUrl = "http://223.3.93.101:8983/solr/real-news";
        HttpSolrClient solrServer = new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
        searchEventLineDao.httpSolrClientNews = solrServer;
        SolrQuery query=new SolrQuery("content:"+"大巴");
        int rows=Integer.parseInt("6");
        query.setRows(rows);
        query.setStart((Integer.parseInt("1")-1)*rows);
        ArrayList<NewsInfo> newsInfos=new ArrayList<NewsInfo>();
        try{
            QueryResponse queryResponse=solrServer.query(query);
            SolrDocumentList solrDocuments=queryResponse.getResults();
            System.out.println(solrDocuments.getNumFound());


        }catch (Exception e){
            e.printStackTrace();

        }


    }
}
