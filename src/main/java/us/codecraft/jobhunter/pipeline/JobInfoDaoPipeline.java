package us.codecraft.jobhunter.pipeline;

import org.springframework.stereotype.Component;
import us.codecraft.jobhunter.dao.JobInfoDAO;
import us.codecraft.jobhunter.model.LieTouJobInfo;
import us.codecraft.webmagic.ResultItems;
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
    public void process(ResultItems resultItems, Task task) {
        if (!resultItems.isSkip()) {
            try {
                Object o = resultItems.get(LieTouJobInfo.class.getCanonicalName());
                if (o!=null){
                    jobInfoDAO.add((LieTouJobInfo) o);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
