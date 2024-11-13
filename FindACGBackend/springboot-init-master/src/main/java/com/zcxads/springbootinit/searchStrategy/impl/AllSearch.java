package com.zcxads.springbootinit.searchStrategy.impl;

import com.zcxads.springbootinit.searchStrategy.SearchStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Component("allSearch")
public class AllSearch implements SearchStrategy {



    @Override
    public List<Map<String, Object>> doSearch(String text) {
        System.out.println("all search");
        return null;
    }
}
