package com.xyc.news.service;

import com.xyc.news.pojo.Category;

import java.util.List;

/**
 * @author xuyuyu
 * @ClassName  CategoryService
 * @Description TODO
 * 2024/3/29  11:06
 */
public interface  CategoryService {
	List<Category> getCategoryList(Category category);

	Boolean categorySave(Category category);

	Boolean categoryUpdate(Category category);

	Boolean categoryDelete(Category category);

	int getTypeById(int id);

	boolean addBach(List<Category> categoryList);
}
