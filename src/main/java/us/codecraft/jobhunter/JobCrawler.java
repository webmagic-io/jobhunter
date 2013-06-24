package us.codecraft.jobhunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import us.codecraft.jobhunter.pageprocessor.LietouJobProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午4:19
 */
@Component
public class JobCrawler {

    @Qualifier("JobInfoDaoPipeline")
    @Autowired
    private Pipeline jobInfoDaoPipeline;

    public void crawl() {
        Spider.create(new LietouJobProcessor()).thread(5).pipeline(jobInfoDaoPipeline).runAsync();
        System.out.println("yes!");
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final JobCrawler jobCrawler = applicationContext.getBean(JobCrawler.class);
        jobCrawler.crawl();
    }
}
