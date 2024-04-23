package com.xyc.news.service;

import com.xyc.news.mapper.NewsMapper;
import com.xyc.news.pojo.News;
import com.xyc.news.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新闻服务
 *
 * @author xuyuyu
 * @ClassName NewsService
 * @Description TODO
 * @date 2024-04-23 10:11:35
 */
@Service
public class NewsService {
    @Autowired
    NewsMapper newsMapper;

    public List<News> getNewsList(News news) {
        return newsMapper.getNewsList(news);
    }
    public Boolean newsSave(News news) {
        news.setCno(UUIDUtils.getUUID());
        int count =  newsMapper.insert(news);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean newsUpdate(News news) {
        int count = newsMapper.updateByPrimaryKey(news);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean newsDelete(News news) {
        int count = newsMapper.deleteByPrimaryKey(news.getCno());
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}
