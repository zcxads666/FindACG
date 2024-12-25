package com.zcxads.springbootinit.spider.adaptee;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class NovelAdaptee implements PageProcessor {
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(10000)
            .setTimeOut(20000)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0")
            .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
            .addHeader("accept-encoding", "gzip, deflate, br, zstd")
            .addHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
            .addHeader("cache-control", "max-age=0")
            .addHeader("cookie", "e2=%7B%22l6%22%3A%22%22%2C%22l7%22%3A%22%22%2C%22l1%22%3A3%2C%22l3%22%3A%22%22%2C%22pid%22%3A%22qd_P_Searchresult%22%2C%22eid%22%3A%22qd_S81%22%7D; e1=%7B%22l6%22%3A%22%22%2C%22l7%22%3A%22%22%2C%22l1%22%3A%22%22%2C%22l3%22%3A%22%22%2C%22pid%22%3A%22qd_P_Searchresult%22%2C%22eid%22%3A%22%22%7D; supportwebp=true; _csrfToken=8CW0lSRNl7lmwW5bQ8eGV0J1wkQSuRSc7XKablkj; traffic_utm_referer=https%3A//www.google.com/; newstatisticUUID=1731498921_2087039915; fu=1803603943; e1=%7B%22l6%22%3A%22%22%2C%22l7%22%3A%22%22%2C%22l1%22%3A2%2C%22l3%22%3A%22%22%2C%22pid%22%3A%22qd_p_qidian%22%2C%22eid%22%3A%22%22%7D; e2=%7B%22l6%22%3A%22%22%2C%22l7%22%3A%22%22%2C%22l1%22%3A2%2C%22l3%22%3A%22%22%2C%22pid%22%3A%22qd_p_qidian%22%2C%22eid%22%3A%22qd_H_Search%22%7D; x-waf-captcha-referer=; w_tsfp=ltvuV0MF2utBvS0Q6qvtnUKnFTEhfTk4h0wpEaR0f5thQLErU5mG0IJzt8LyMHLa6sxnvd7DsZoyJTLYCJI3dwNHEMnHd4tD2A3Ck4Us2Y1FUxJuEZ/VDFMdJuhzvTIXdXhCNxS00jA8eIUd379yilkMsyN1zap3TO14fstJ019E6KDQmI5uDW3HlFWQRzaLbjcMcuqPr6g18L5a5T3b4QmpfQghB7IR2ReR1i4fXCt3shazfegMNhurJsv6SqA=")
            .addHeader("priority", "u=0, i")
            .addHeader("sec-ch-ua", "\"Chromium\";v=\"130\", \"Microsoft Edge\";v=\"130\", \"Not?A_Brand\";v=\"99\"")
            .addHeader("sec-ch-ua-mobile", "?0")
            .addHeader("sec-ch-ua-platform", "\"Windows\"")
            .addHeader("sec-fetch-dest", "document")
            .addHeader("sec-fetch-mode", "navigate")
            .addHeader("sec-fetch-site", "none")
            .addHeader("sec-fetch-user", "?1")
            .addHeader("upgrade-insecure-requests", "1");

    @Override
    public void process(Page page) {
        // 使用 XPath 提取标题内容
        List<String> titles = page.getHtml().xpath("//*[@id=\"result-list\"]/div/ul/li/div[2]/h3/a/@title").all();
        List<String> hrefs = page.getHtml().xpath("//*[@id=\"result-list\"]/div/ul/li/div[2]/h3/a/@href").all();
        List<String> fullTags = new ArrayList<>();
        for (String href : hrefs) {

            fullTags.add("qiDian");
        }
        // 存储提取到的标题信息
        page.putField("titles", titles);
        page.putField("hrefs", hrefs);
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
            String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
            return "https://www.qidian.com/so/" + encodedKeyword;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
