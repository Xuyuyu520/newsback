package com.xyc.news.service;

import com.xyc.news.mapper.FileInfoMapper;
import com.xyc.news.pojo.FileInfo;
import com.xyc.news.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuyuyu
 * @ClassName FileInfoService
 * @Description TODO
 */
@Service
public class FileInfoService {
    @Autowired
    FileInfoMapper fileInfoMapper;

    public List<FileInfo> getFileInfoList(FileInfo fileInfo) {
        return fileInfoMapper.getFileInfoList(fileInfo);
    }
    public Boolean fileInfoSave(FileInfo fileInfo) {
        fileInfo.setId(UUIDUtils.getUUID());
        int count =  fileInfoMapper.insert(fileInfo);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean fileInfoUpdate(FileInfo fileInfo) {
        int count = fileInfoMapper.updateByPrimaryKey(fileInfo);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean fileInfoDelete(FileInfo fileInfo) {
        int count = fileInfoMapper.deleteByPrimaryKey(fileInfo.getId());
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}
