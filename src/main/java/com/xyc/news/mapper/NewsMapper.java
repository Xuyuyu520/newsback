package com.xyc.news.mapper;

import com.xyc.news.pojo.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface NewsMapper {

    int deleteByPrimaryKey(String cno);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(String cno);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> getNewsList(News news);
}
