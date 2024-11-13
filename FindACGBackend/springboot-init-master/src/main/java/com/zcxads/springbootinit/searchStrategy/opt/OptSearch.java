package com.zcxads.springbootinit.searchStrategy.opt;

import com.zcxads.springbootinit.searchStrategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OptSearch {
    private SearchStrategy searchStrategy;

    public OptSearch(@Qualifier("allSearch")SearchStrategy searchStrategy)
    {
        this.searchStrategy = searchStrategy;
    }

    public void setSearch(SearchStrategy searchStrategy)
    {
        this.searchStrategy = searchStrategy;
    }

    public List<Map<String, Object>>  doSearch(String text)
        {
            return searchStrategy.doSearch(text);
        }
}
