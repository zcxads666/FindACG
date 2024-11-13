package com.zcxads.springbootinit.searchStrategy.impl;

import com.zcxads.springbootinit.searchStrategy.SearchStrategy;
import com.zcxads.springbootinit.spider.ResultPipeline;
import com.zcxads.springbootinit.spider.adaptee.NovelAdaptee;
import com.zcxads.springbootinit.spider.adapter.spiderAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.util.List;
import java.util.Map;



@Component("novelSearch")
public class NovelSearch implements SearchStrategy {
    @Override
    public List<Map<String, Object>> doSearch(String text) {
        spiderAdapter novelSearch = new spiderAdapter(new NovelAdaptee());
        List<Map<String, Object>> search = novelSearch.doSearchNovel(text);
        return search;
    }


}
