package com.funyoo.hqxApp.controller;

import com.funyoo.hqxApp.model.SchoolActivityModel;
import com.funyoo.hqxApp.model.User;
import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.service.SchActivityService;
import com.funyoo.hqxApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/SchoolActivity")
public class SchoolActivityController {

    @Autowired
    SchActivityService actSrv;

    @Autowired
    UserService userService;

    @RequestMapping("/getActivities")
    public Result<List<SchoolActivityModel>> getActivities() {
        List<SchoolActivityModel> activities = null;
        activities = actSrv.getActivities();
        if (activities == null || activities.size() == 0) {
            return Result.error(CodeMsg.NO_DATA_ERROR);
        }
        return Result.success(activities);
    }

    @RequestMapping("/build")
    public Result<Boolean> build() {
        // TODO 创建活动 创建时会先将活动创建到活动列表中 再通过活动id 创建活动参与表

        return Result.success(true);
    }

    /**
     * 参加活动
     * @param id    活动id
     * @param stuId 学生id
     * @return
     */
    @RequestMapping("/join")
    public Result<Boolean> join(Integer id, Integer stuId) {
        // 参加活动
        User user = userService.getUserById(stuId);
        boolean result = actSrv.join(id, user);
        return Result.success(result);
    }

    @RequestMapping("/isJoin")
    public Result<Boolean> isJoin(Integer id, Integer stuId) {
        // 参加活动
        boolean result = actSrv.isJoin(id, stuId);
        return Result.success(result);
    }

    @RequestMapping("/cancel")
    public Result<Boolean> cancel(Integer id, Integer stuId) {
        // 取消参加活动
        boolean result = actSrv.cancel(id, stuId);
        return Result.success(true);
    }

}
