package com.funyoo.hqxApp.dao;

import com.funyoo.hqxApp.model.EmailModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmailDao {

    @Select("select * from email")
    List<EmailModel> getRecivers();
}
