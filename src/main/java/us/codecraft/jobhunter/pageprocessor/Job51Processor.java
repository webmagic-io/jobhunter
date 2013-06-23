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
public class Job51Processor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        final Selectable links = page.getHtml().links();
        page.addTargetRequests(links.regex(".*search\\.51job\\.com/jobsearch/search_result\\.php.*").toStrings());
        page.addTargetRequests(links.regex("http://search\\.51job\\.com/job/.*\\.html").toStrings());
        if (!page.getUrl().toString().contains("http://search.51job.com/job/")) {
            page.setSkip(true);
            return;
        }
        final Selectable jobDiv = page.getHtml().xpath("//div[@class='s_txt_jobs']");
        JobInfo jobInfo = new JobInfo();
        jobInfo.setTitle(jobDiv.regex("<td class=\"sr_bt\" colspan=\"2\">([^<>]*)</td>").toString());
        jobInfo.setCompany(page.getHtml().xpath("//div[@class=\"main-view\"]/h2").replace("<sup>.*</sup>", "").toString());
        jobInfo.setSalary(page.getHtml().xpath("//div[@class='salary']//em").toString());
        jobInfo.setSource("lietou.com");
        jobInfo.setDescription(page.getHtml().regex("岗位职责：(.*?)岗位要求：").replace("\\s*<[^<>]+>\\s*", "").toString());
        jobInfo.setRequirement(page.getHtml().regex("岗位要求：(.*?)薪酬福利：").replace("\\s*<[^<>]+>\\s*", "").toString());
        jobInfo.setUrl(page.getUrl().toString());
        page.<JobInfo>setExtra(jobInfo);
    }

    @Override
    public Site getSite() {
        if (site == null) {
            site = Site.me().setDomain("51job.com").addStartUrl("http://search.51job.com/list/020000%252C00,%2B,0106%252C0107,01,8,%2B,%2B,1,%2B.html?lang=c&stype=2&postchannel=0000&funtype_big=0106,0107&postfrom=020000&btnSltPosition=...&image_x=42&image_y=13&specialarea=00").
                    setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                    .setSleepTime(0);
        }
        return site;
    }
}
