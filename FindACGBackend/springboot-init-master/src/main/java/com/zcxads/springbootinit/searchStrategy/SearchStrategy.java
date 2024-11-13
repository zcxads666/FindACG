package com.zcxads.springbootinit.searchStrategy;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {
    List<Map<String, Object>> doSearch(String text);
}
