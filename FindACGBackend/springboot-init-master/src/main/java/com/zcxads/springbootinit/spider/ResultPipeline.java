package com.zcxads.springbootinit.spider;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultPipeline implements Pipeline {
    private List<Map<String, Object>> results = new ArrayList<>();

    @Override
    public void process(ResultItems resultItems, Task task) {
        results.add(resultItems.getAll());
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }
}
