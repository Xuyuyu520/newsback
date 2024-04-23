package com.xyc.news.service.impl;


import com.xyc.news.mapper. CategoryMapper;
import com.xyc.news.pojo.Category;
import com.xyc.news.service. CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuyuyu
 * @ClassName  CategoryServiceImpl
 * @Description TODO
 */
@Service
public class  CategoryServiceImpl implements  CategoryService {
    @Autowired
     CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoryList(Category category) {
        return categoryMapper.getCategoryList(category);
    }
    @Override
    public Boolean categorySave(Category category) {
        int count =  categoryMapper.insert(category);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean categoryUpdate(Category category) {
        int count = categoryMapper.updateByPrimaryKey(category);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean categoryDelete(Category category) {
        int count = categoryMapper.deleteByPrimaryKey(category.getTypeId());
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getTypeById(int id){
        Category category = new Category();
        category.setTypeId(id);
        return categoryMapper.getCategoryList(category).size();
    }

    @Override
    public boolean addBach(List<Category> categoryList) {
        int count = categoryMapper.addBach(categoryList);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}
