package com.zcxads.springbootinit.spider.adapter;

import com.zcxads.springbootinit.searchStrategy.impl.NovelSearch;
import com.zcxads.springbootinit.spider.ResultPipeline;
import com.zcxads.springbootinit.spider.adaptee.BiliAdaptee;
import com.zcxads.springbootinit.spider.adaptee.GameAdaptee;
import com.zcxads.springbootinit.spider.adaptee.NovelAdaptee;
import com.zcxads.springbootinit.spider.spiderBase;
import us.codecraft.webmagic.Spider;

import java.util.List;
import java.util.Map;



public class spiderAdapter implements spiderBase {

    private NovelAdaptee novelAdaptee;
    private BiliAdaptee biliAdaptee;

    private GameAdaptee gameAdaptee;
    public spiderAdapter(NovelAdaptee novelAdaptee) {
        this.novelAdaptee = novelAdaptee;
    }

    public spiderAdapter(BiliAdaptee biliAdaptee) {
        this.biliAdaptee = biliAdaptee;
    }

    public spiderAdapter(GameAdaptee gameAdaptee) {
        this.gameAdaptee = gameAdaptee;
    }

    public List<Map<String, Object>> doSearchNovel(String text) {
        String keyword = text;
        String url = novelAdaptee.buildUrl(keyword);

        if (url != null) {
            // 创建自定义的 Pipeline 来存储结果
            ResultPipeline resultPipeline = new ResultPipeline();

            // 创建爬虫
            Spider.create(new NovelAdaptee())
                    .addUrl(url)
                    .addPipeline(resultPipeline)
                    .thread(3)
                    .run();

            // 打印获取到的结果
            List<Map<String, Object>> results = resultPipeline.getResults();
            for (Map<String, Object> result : results) {
                System.out.println(result);
            }
            return results;
        } else {
            System.out.println("URL 构建失败");
        }
        return null;
    }

    public List<Map<String, Object>> doSearchGame(String text) {
        String keyword = text;
        String url = gameAdaptee.buildUrl(keyword);

        if (url != null) {
            // 创建自定义的 Pipeline 来存储结果
            ResultPipeline resultPipeline = new ResultPipeline();

            // 创建爬虫
            Spider.create(new NovelAdaptee())
                    .addUrl(url)
                    .addPipeline(resultPipeline)
                    .thread(3)
                    .run();

            // 打印获取到的结果
            List<Map<String, Object>> results = resultPipeline.getResults();
            for (Map<String, Object> result : results) {
                System.out.println(result);
            }
            return results;
        } else {
            System.out.println("URL 构建失败");
        }
        return null;
    }

}
