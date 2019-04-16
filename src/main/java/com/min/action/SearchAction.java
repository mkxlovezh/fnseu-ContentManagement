package com.min.action;

import com.min.entity.NewsInfo;
import com.min.service.SearchService;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

public class SearchAction {

    @Resource
    private SearchService searchService;
    private String keywords;
    private JSONObject result;
    private String page;
    private String pageNum;
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String execute(){
        Map<String,Object> news=searchService.getKeywordsNews(keywords,page,pageNum);
        JSONObject json=JSONObject.fromObject(news);
        setResult(json);
        System.out.println("success");
        return "success";
    }
}
