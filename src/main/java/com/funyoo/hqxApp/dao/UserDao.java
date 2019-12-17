package com.funyoo.hqxApp.dao;

import com.funyoo.hqxApp.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

    @Select("SELECT * FROM user_information WHERE mail = #{mail};")
    User getUserByMail(@Param("mail") String mail);

    @Insert("INSERT INTO user_information " +
            "(name, school_id, class_str, showing, nick, head_image, tel, mail, password)" +
            " VALUES " +
            "(#{user.name}, #{user.schoolId}, #{user.classStr}, #{user.showing}, #{user.nick}, #{user.headImage}, #{user.tel}, #{user.mail}, #{user.password});")
    boolean insertUser(@Param("user") User user);

    @Update("UPDATE user_information SET " +
            "name = #{user.name}, " +
            "school_id = #{user.schoolId}, " +
            "class_str = #{user.classStr}, " +
            "showing = #{user.showing}, " +
            "nick = #{user.nick}, " +
            "head_image = #{user.headImage}, " +
            "tel = #{user.tel} " +
            "where id = #{user.id}")
    boolean updateUserMess(@Param("user") User user);

    @Select("SELECT * FROM user_information WHERE id = #{id}")
    User getUserById(@Param("id") Integer stuId);
}
