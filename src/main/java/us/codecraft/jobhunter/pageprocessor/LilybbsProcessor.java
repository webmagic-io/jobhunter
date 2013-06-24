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
public class LilybbsProcessor implements PageProcessor{

    private Site site;

    @Override
    public void process(Page page) {
        final Selectable links = page.getHtml().links();
        page.addTargetRequests(links.regex(".*bbs\\.nju\\.edu\\.cn.*").toStrings());
        if (!page.getUrl().toString().contains("file=")) {
            page.setSkip(true);
            return;
        }
    }

    @Override
    public Site getSite() {
        if (site==null){
            site = Site.me().setDomain("bbs.nju.edu.cn").addStartUrl("http://bbs.nju.edu.cn/bbstdoc?board=JobAndWork").setSleepTime(0);
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new LilybbsProcessor()).thread(10).pipeline(new FilePipeline("/data/jobhunter")).run();
    }
}
