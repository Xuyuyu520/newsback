package com.xyc.news.service;

import com.xyc.news.mapper.FileTypeMapper;
import com.xyc.news.pojo.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件类型服务
 *
 * @author xuyuyu
 * @ClassName FileTypeService
 * @Description TODO
 * @date 2024-04-23 10:11:39
 */
@Service
public class FileTypeService {
    @Autowired
    FileTypeMapper fileTypeMapper;

    public List<FileType> getFileTypeList(FileType fileType) {
        return fileTypeMapper.getFileTypeList(fileType);
    }
    public Boolean fileTypeSave(FileType fileType) {
        int count =  fileTypeMapper.insert(fileType);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean fileTypeUpdate(FileType fileType) {
        int count = fileTypeMapper.updateByPrimaryKey(fileType);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean fileTypeDelete(FileType fileType) {
        int count = fileTypeMapper.deleteByPrimaryKey(fileType.getTypeId());
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}
