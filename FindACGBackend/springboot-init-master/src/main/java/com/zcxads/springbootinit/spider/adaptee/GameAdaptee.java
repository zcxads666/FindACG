package com.zcxads.springbootinit.spider.adaptee;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class GameAdaptee implements PageProcessor {
    private String keywordThis="";
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(2000)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0")
           .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
           .addHeader("Cache-Control", "max-age=0")
           .addHeader("Connection", "keep-alive")
           .addHeader("Upgrade-Insecure-Requests", "1");

    @Override
    public void process(Page page) {
        // 使用 XPath 提取标题内容
        List<String> titles = page.getHtml().xpath("//div[@class='searchresults']//li//a/@title").all();
        List<String> hrefs = page.getHtml().xpath("//div[@class='searchresults']//li//a/@href").all();
        List<String> firstTitles = page.getHtml().xpath("/html/head/title").all();

        // 存储提取到的标题信息
        List<String> fullHrefs = new ArrayList<>();
        List<String> fullTags = new ArrayList<>();
        if(hrefs.size()==0 && !firstTitles.isEmpty())
        {

            String firstTitle = firstTitles.get(0); // 假设只有一个 firstTitle
            if (firstTitle.contains(keywordThis)) {
                fullHrefs.add("https://mzh.moegirl.org.cn/" + keywordThis);
                fullTags.add("Wiki");
                titles.add(keywordThis);
                System.out.println("Keyword '" + keywordThis + "' found in firstTitle: " + firstTitle);
            } else {
                System.out.println("Keyword '" + keywordThis + "' not found in firstTitle: " + firstTitle);
            }


        }else {
            System.out.println("No firstTitle found.");

            for (String href : hrefs) {
            fullHrefs.add("https://mzh.moegirl.org.cn" + href);
            fullTags.add("Wiki");
        }

        }



        // 存储提取到的标题信息
        page.putField("titles", titles);
        page.putField("hrefs", fullHrefs);
        page.putField("tags", fullTags);
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    /**
     * 将中文关键词进行 URL 编码，并拼接到起始 URL
     *
     * @param keyword 中文关键词
     * @return 编码后的 URL
     */
    public String buildUrl(String keyword) {
        try {
            keywordThis = keyword;
            String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
            return "https://mzh.moegirl.org.cn/index.php?search=" + encodedKeyword;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
