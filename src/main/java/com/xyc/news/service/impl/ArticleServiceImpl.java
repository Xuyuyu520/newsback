package com.xyc.news.service.impl;

import com.xyc.news.pojo.Article;
import com.xyc.news.service.ArticleService;
import com.xyc.news.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xyc.news.mapper.ArticleMapper;

import java.util.List;

/**
 * @author xuyuyu
 * @ClassName categoryService
 * @Description TODO
 * 2023/2/19  9:47
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleMapper ArticleMapper;

	@Override
	public List<Article> getArticleList(Article article) {
		return ArticleMapper.getArticleList(article);
	}

	@Override
	public Boolean articleSave(Article article) {
		article.setArticleId(UUIDUtils.getUUID());
		int count = ArticleMapper.insert(article);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public Boolean articleUpdate(Article article) {
		int count = ArticleMapper.updateByPrimaryKey(article);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean articleDelete(Article article) {
		int count = ArticleMapper.deleteByPrimaryKey(article.getArticleId());
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
}
