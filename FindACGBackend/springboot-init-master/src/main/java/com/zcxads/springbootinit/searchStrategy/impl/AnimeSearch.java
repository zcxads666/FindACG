package com.zcxads.springbootinit.searchStrategy.impl;

import com.zcxads.springbootinit.searchStrategy.SearchStrategy;
import com.zcxads.springbootinit.spider.adaptee.GameAdaptee;
import com.zcxads.springbootinit.spider.adapter.spiderAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public class AnimeSearch implements SearchStrategy {
    @Override
    public List<Map<String, Object>> doSearch(String text) {
        spiderAdapter animeSearch = new spiderAdapter(new GameAdaptee());
        List<Map<String, Object>> search = animeSearch.doSearchGame(text);
        return search;
    }
}
