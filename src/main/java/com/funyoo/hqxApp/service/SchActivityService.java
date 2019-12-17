package com.funyoo.hqxApp.service;

import com.funyoo.hqxApp.config.AppConfig;
import com.funyoo.hqxApp.dao.SchActDao;
import com.funyoo.hqxApp.model.Article;
import com.funyoo.hqxApp.model.SchoolActivityModel;
import com.funyoo.hqxApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchActivityService {

    @Autowired
    public AppConfig appConfig;

    @Autowired
    public SchActDao schActDao;

    /**
     * 获得所有活动
     * @return
     */
    public List<SchoolActivityModel> getActivities() {
        List result = null;
        result = schActDao.getActivities();
        if (result != null) {
            setActivitiesFilePath(result, "school");
        }
        return result;
    }

    /**
     * 参加活动
     * @param id    活动id
     * @param user  用户
     * @return
     */
    public boolean join(Integer id, User user) {
        Integer join = schActDao.isJoin(id, user.getId());
        if (join == null) {
            return schActDao.insert(id, user);
        } else {
            return schActDao.join(id, user.getId());
        }
    }

    /**
     * 取消参加
     * @param id   活动id
     * @param uid  用户id
     * @return
     */
    public boolean cancel(Integer id, Integer uid) {
        return schActDao.cancel(id, uid);
    }

    public boolean isJoin(Integer id, Integer stuId) {
        Integer join = schActDao.isJoin(id, stuId);
        if (join == null || join == 0) {
            return false;
        }
        return true;
    }

    /**
     * 给文章引用的文件添加前置路径
     * 此举是方便客户端以访问静态资源服务器来获取资源
     * @param activity
     * @param part
     */
    private void setActivityFilePath(SchoolActivityModel activity, String part) {
        activity.setPic1(appConfig.getStaticUrl() + part + "/image/" + activity.getPic1());
        activity.setPic2(appConfig.getStaticUrl() + part + "/image/" + activity.getPic2());
        activity.setPic3(appConfig.getStaticUrl() + part + "/image/" + activity.getPic3());
    }
    private void setActivitiesFilePath(List<SchoolActivityModel> activities, String part) {
        for (SchoolActivityModel activity : activities) {
            setActivityFilePath(activity, part);
        }
    }
}
