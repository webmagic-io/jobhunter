package us.codecraft.jobhunter.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-24
 *         Time: 上午7:08
 */
public class NewSmthProcessor implements PageProcessor{

    private Site site;

    @Override
    public void process(Page page) {
        final Selectable links = page.getHtml().links();
        page.addTargetRequests(links.regex(".*#!board/Career_Campus.*").toStrings());
        page.addTargetRequests(links.regex(".*/nForum/article/Career_Campus/.*").toStrings());
        if (!page.getUrl().toString().contains("article")) {
            page.setSkip(true);
            return;
        }
    }

    @Override
    public Site getSite() {
        if (site==null){
            site = Site.me().setDomain("www.newsmth.net").addStartUrl("http://www.newsmth.net/nForum/#!board/Career_Campus").setSleepTime(0);
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new NewSmthProcessor()).thread(5).pipeline(new FilePipeline("/data/jobhunter")).run();
    }
}
