package com.zcxads.springbootinit.searchStrategy.impl;

import com.zcxads.springbootinit.searchStrategy.SearchStrategy;
import com.zcxads.springbootinit.spider.adaptee.GameAdaptee;
import com.zcxads.springbootinit.spider.adaptee.NovelAdaptee;
import com.zcxads.springbootinit.spider.adapter.spiderAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("allSearch")
public class AllSearch implements SearchStrategy {



    @Override
    public List<Map<String, Object>> doSearch(String text) {
        spiderAdapter novelSearch = new spiderAdapter(new NovelAdaptee());
        List<Map<String, Object>> searchNovel = novelSearch.doSearchNovel(text);
        spiderAdapter animeSearch = new spiderAdapter(new GameAdaptee());
        List<Map<String, Object>> searchGame = animeSearch.doSearchGame(text);
        List<Map<String, Object>> combinedList = new ArrayList<>();

    // 将 searchGame 的所有元素添加到 combinedList 中
    combinedList.addAll(searchGame);
    // 将 searchNovel 的所有元素添加到 combinedList 中
    combinedList.addAll(searchNovel);



        return combinedList;
    }
}
