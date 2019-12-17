package com.funyoo.hqxApp.dao;

import com.funyoo.hqxApp.model.Article;
import com.funyoo.hqxApp.model.CollectionModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectionDao {

    @Select("SELECT * FROM user_collection_${id} WHERE id < #{num} ORDER BY id DESC LIMIT #{readNum}")
    public List<CollectionModel> getCollectionsByNum(@Param("id") Integer id, @Param("num") Integer num, @Param("readNum") Integer OnceReadNum);

    @Select("SELECT * FROM user_collection_${id} ORDER BY id DESC LIMIT #{readNum}")
    public List<CollectionModel> getCollectionsLimit(@Param("id") Integer id, @Param("readNum") Integer OnceReadNum);

    @Select("SELECT * FROM user_collection_${id} ORDER BY id DESC")
    List<CollectionModel> getCollections(@Param("id") Integer id);



    @Select("select count(*) from information_schema.TABLES t where t.TABLE_NAME ='user_collection_${id}';")
    public int tableIsExixt(@Param("id") Integer id);

    @Update("CREATE TABLE `user_collection_${id}` (\n" +
            "\t`id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "\t`aid` int(11) NOT NULL COMMENT '文章id',\n" +
            "\t`part` varchar(255) NOT NULL COMMENT '文章类目',\n"+
            "\t`title` varchar(255) NOT NULL COMMENT '文章标题',\n"+
            "\t`type` varchar(255) NOT NULL COMMENT '类目',\n" +
            "\t`html_url` varchar(255) NOT NULL COMMENT '文本html路径',\n" +
            "\t`date` date NOT NULL,\n" +
            "\t`created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
            "\tPRIMARY KEY (`id`),\n" +
            "\tUNIQUE `article` USING BTREE (`aid`, `part`) comment '',\n" +
            "\tINDEX `date` USING BTREE (`date`) comment ''\n" +
            ") ENGINE=`InnoDB` AUTO_INCREMENT=4 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='新闻模块' CHECKSUM=0 DELAY_KEY_WRITE=0;")
    public boolean buildTable(@Param("id") Integer id);

    @Insert("INSERT INTO user_collection_${uid} (aid, part, title, type, html_url, date) " +
            "VALUES " +
            "(#{a.id}, #{part}, #{a.title}, #{a.type}, #{a.htmlUrl}, #{a.date})")
    boolean doCollect(@Param("uid") Integer id, @Param("part") String part, @Param("a") Article article);

    @Select("SELECT id from user_collection_${uid} WHERE aid = #{aid} AND part = #{part}")
    Integer isCollected(@Param("uid") Integer uid, @Param("part") String part, @Param("aid") Integer artId) throws Throwable;

    @Delete("DELETE FROM user_collection_${uid} WHERE aid = #{aid} AND part = #{part}")
    boolean removeCollect(@Param("uid") Integer id, @Param("part") String part, @Param("aid") Integer artId) throws Throwable;
}
