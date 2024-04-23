package com.xyc.news.service;

import com.xyc.news.pojo.Article;

import java.util.List;

/**
 * @author xuyuyu
 * @ClassName ArticleService
 * @Description TODO
 * 2024/3/29  11:02
 */
public interface ArticleService {
    List<Article> getArticleList(Article article);

    Boolean articleSave(Article article);

    Boolean articleUpdate(Article article);

    Boolean articleDelete(Article article);

}
