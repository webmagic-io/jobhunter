package us.codecraft.jobhunter.dao;

import org.apache.ibatis.annotations.Insert;
import us.codecraft.jobhunter.model.LieTouJobInfo;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午4:27
 */
public interface JobInfoDAO {

    @Insert("insert into JobInfo (`title`,`salary`,`company`,`description`,`source`,`url`,`urlMd5`) values (#{title},#{salary},#{company},#{description},#{source},#{url},#{urlMd5})")
    public int add(LieTouJobInfo jobInfo);
}
