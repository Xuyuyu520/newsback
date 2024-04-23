package com.xyc.news.mapper;

import com.xyc.news.pojo.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FileInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    List<FileInfo> getFileInfoList(FileInfo fileInfo);
}
