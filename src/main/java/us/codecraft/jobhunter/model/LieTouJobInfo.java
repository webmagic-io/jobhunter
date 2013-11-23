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
@TargetUrl("http://a.lietou.com/\\d+/job_\\d+.shtml")
@HelpUrl("*sojob/\\?setdefault=true&curPage=\\d+")
public class LieTouJobInfo implements AfterExtractor {
    @ExtractBy("//div[@class=\"main-view\"]/h1")
    private String title="";
    @ExtractBy("//div[@class='salary']//em")
    private String salary="";
    @ExtractBy("//div[@class=\"main-view\"]/h2")
    private String company="";
    @ExtractBy(value = "岗位职责：(.*?)岗位要求：",type = ExtractBy.Type.Regex)
    private String description="";
    @ExtractBy(value = "岗位要求：(.*?)薪酬福利：",type = ExtractBy.Type.Regex)
    private String requirement="";
    private String source="lietou.com";
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

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        if (requirement!=null){
            this.requirement = requirement;
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
                ", requirement='" + requirement + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public void afterProcess(Page page) {
        System.out.println(page);
    }
}
