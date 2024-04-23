package com.xyc.news.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import com.xyc.news.common.Page;
import com.xyc.news.pojo.News;
import com.xyc.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xuyuyu
 * @ClassName NewsController
 * @Description TODO
 */
@RestController
@CrossOrigin
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @GetMapping("/getNewsList")
    public Wrapper<PageInfo<News>> getNewsList(Page page, News news) {
        PageHelper.startPage((int)page.getCurrent(),(int)page.getSize());
        List<News> newsList = newsService.getNewsList(news);
        PageInfo<News> newsPageInfo = new PageInfo<>(newsList);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,newsPageInfo);
    }
    @GetMapping("/getAllNews")
    public Wrapper<List<News> > getAllNews() {
        List<News> newsList = newsService.getNewsList(new News());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,newsList);
    }
    @PostMapping("/newsSave")
    public Wrapper<Boolean> newsSave(@RequestBody News news) {
        Boolean saveResult = newsService.newsSave(news);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,saveResult);
    }
    @PatchMapping("/newsUpdate")
    public Wrapper<Boolean> newsUpdate(@RequestBody News news) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,newsService.newsUpdate(news));
    }
    @DeleteMapping("/newsDelete")
    public Wrapper<Boolean> newsDelete(News news) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,newsService.newsDelete(news));
    }
}
