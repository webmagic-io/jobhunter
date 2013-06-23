package us.codecraft.jobhunter.model;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午4:28
 */
public class JobInfo {
    private String title="";
    private String salary="";
    private String company="";
    private String description="";
    private String requirement="";
    private String source="";
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
}
