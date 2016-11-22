package us.codecraft.jobhunter.model;

import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午4:28
 */
@TargetUrl("https://www.liepin.com/job/*")
@HelpUrl("https://www.liepin.com/sojob/?dqs=020&curPage=\\d+")
public class LieTouJobInfo implements AfterExtractor {
    @ExtractBy("//h1/text()")
    private String title="";
    @ExtractBy("//p[@class='job-item-title']/text()")
    private String salary="";
    @ExtractBy("//div[@class='title-info']/h3/a/text()")
    private String company="";
    @ExtractBy("//div[@class='content content-word']/allText()")
    private String description="";
    private String source="liepin.com";
    @ExtractByUrl
    private String url="";
    private String urlMd5="";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description!=null){
            this.description = description;
        }
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.urlMd5 = DigestUtils.md5Hex(url);
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "title='" + title + '\'' +
                ", salary='" + salary + '\'' +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public void afterProcess(Page page) {
    }
}
