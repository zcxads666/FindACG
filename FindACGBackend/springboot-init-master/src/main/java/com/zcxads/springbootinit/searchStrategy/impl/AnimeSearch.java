package com.zcxads.springbootinit.searchStrategy.impl;

import com.zcxads.springbootinit.searchStrategy.SearchStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public class AnimeSearch implements SearchStrategy {
    @Override
    public List<Map<String, Object>> doSearch(String text) {
        System.out.println("AnimeSearch");
        return null;
    }
}
