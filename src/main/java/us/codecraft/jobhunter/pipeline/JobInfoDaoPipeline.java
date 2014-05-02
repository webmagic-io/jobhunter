package us.codecraft.jobhunter.pipeline;

import org.springframework.stereotype.Component;
import us.codecraft.jobhunter.dao.JobInfoDAO;
import us.codecraft.jobhunter.model.LieTouJobInfo;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午8:56
 */
@Component("JobInfoDaoPipeline")
public class JobInfoDaoPipeline implements PageModelPipeline<LieTouJobInfo> {

    @Resource
    private JobInfoDAO jobInfoDAO;

    @Override
    public void process(LieTouJobInfo lieTouJobInfo, Task task) {
        jobInfoDAO.add(lieTouJobInfo);
    }
}
