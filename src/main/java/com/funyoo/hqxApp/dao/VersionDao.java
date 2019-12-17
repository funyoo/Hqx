package com.funyoo.hqxApp.dao;

import com.funyoo.hqxApp.model.Version;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VersionDao {

    @Select("SELECT * FROM version ORDER BY id DESC LIMIT 1")
    public Version getNewVersion() throws Exception;
}
