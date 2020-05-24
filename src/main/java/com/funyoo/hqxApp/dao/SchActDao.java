package com.funyoo.hqxApp.dao;

import com.funyoo.hqxApp.model.SchoolActivityModel;
import com.funyoo.hqxApp.model.User;
import com.funyoo.hqxApp.vo.ActMember;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SchActDao {

    @Select("SELECT * FROM school_activity WHERE end = 0")
    public List<SchoolActivityModel> getActivities();

    @Insert("INSERT INTO school_activity_${id} " +
            "(user_id, mail, tel, class_str, school_id, name, is_join) " +
            "VALUES (#{u.id}, #{u.mail}, #{u.tel}, #{u.classStr}, #{u.schoolId}, #{u.name}, 1) ")
    boolean insert(@Param("id") Integer id, @Param("u") User user);

    @Update("UPDATE school_activity_${id} " +
            "SET cancel = cancel + 1, is_join = 0 " +
            "WHERE user_id = #{uid}")
    boolean cancel(@Param("id") Integer id, @Param("uid") Integer uid);

    @Select("SELECT is_join FROM school_activity_${id} WHERE user_id = #{uid}")
    Integer isJoin(@Param("id") Integer id, @Param("uid") Integer uid);

    @Update("UPDATE school_activity_${id} " +
            "SET is_join = 1 " +
            "WHERE user_id = #{uid}")
    boolean join(@Param("id") Integer id, @Param("uid") Integer id1);

    @Select("SELECT * from school_activity_${id} WHERE is_join = 1")
    public List<ActMember> getMember(@Param("id") Integer id);

//    @Select("SELECT * FROM school_activity WHERE end = 0 and when > DAY(2019/4/4)")
//    public List<SchoolActivityModel> getActivities(int index, int num);
}
