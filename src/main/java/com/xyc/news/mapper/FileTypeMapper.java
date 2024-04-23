package com.xyc.news.mapper;

import com.xyc.news.pojo.FileType;

import java.util.List;

public interface FileTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileType record);

    int insertSelective(FileType record);

    FileType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileType record);

    int updateByPrimaryKey(FileType record);

    List<FileType> getFileTypeList(FileType fileType);
}
