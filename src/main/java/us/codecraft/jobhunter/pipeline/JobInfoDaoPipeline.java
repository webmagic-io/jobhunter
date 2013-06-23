package us.codecraft.jobhunter.pipeline;

import org.springframework.stereotype.Component;
import us.codecraft.jobhunter.dao.JobInfoDAO;
import us.codecraft.jobhunter.model.JobInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午8:56
 */
@Component("JobInfoDaoPipeline")
public class JobInfoDaoPipeline implements Pipeline {

    @Resource
    private JobInfoDAO jobInfoDAO;

    @Override
    public void process(Page page, Task task) {
        if (!page.isSkip()) {
            try {
                jobInfoDAO.add((JobInfo) page.getExtra());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
