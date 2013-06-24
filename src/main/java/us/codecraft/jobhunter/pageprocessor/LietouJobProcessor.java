package us.codecraft.jobhunter.pageprocessor;

import us.codecraft.jobhunter.model.JobInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午4:33
 */
public class LietouJobProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        final Selectable links = page.getHtml().links();
        page.addTargetRequests(links.regex(".*sojob/\\?setdefault=true&curPage=\\d+").toStrings());
        page.addTargetRequests(links.regex(".*a\\.lietou\\.com/\\d+/job_\\d+\\.shtml").toStrings());
        if (!page.getUrl().toString().contains("http://a.lietou.com/")) {
            page.setSkip(true);
            return;
        }
        JobInfo jobInfo = new JobInfo();
        jobInfo.setTitle(page.getHtml().xpath("//div[@class=\"main-view\"]/h1").toString());
        jobInfo.setCompany(page.getHtml().xpath("//div[@class=\"main-view\"]/h2").replace("<sup>.*</sup>","").toString());
        jobInfo.setSalary(page.getHtml().xpath("//div[@class='salary']//em").toString());
        jobInfo.setSource("lietou.com");
        jobInfo.setDescription(page.getHtml().regex("岗位职责：(.*?)岗位要求：").replace("\\s*<[^<>]+>\\s*","\n").toString());
        jobInfo.setRequirement(page.getHtml().regex("岗位要求：(.*?)薪酬福利：").replace("\\s*<[^<>]+>\\s*","\n").toString());
        jobInfo.setUrl(page.getUrl().toString());
        page.<JobInfo>setExtra(jobInfo);
    }

    @Override
    public Site getSite() {
        if (site == null) {
            site = Site.me().setDomain("lietou.com").addStartUrl("http://www.lietou.com/sojob/?dqs=020&curPage=0").
                    setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                    .addCookie("_uuid","70533C2F7F00000119D2052B5D6F5571").addCookie("Hm_lvt_44e2d607eedc861167a48c933fa49618=","1371979168,1371979650,1371980918;").setSleepTime(0);
        }
        return site;
    }
}
