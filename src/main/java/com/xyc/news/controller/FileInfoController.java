package com.xyc.news.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.news.common.Page;
import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import com.xyc.news.pojo.FileInfo;
import com.xyc.news.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xuyuyu
 * @ClassName FileInfoController
 * @Description TODO
 */
@RestController
@CrossOrigin
@RequestMapping("/fileInfo")
public class FileInfoController {
    @Autowired
    FileInfoService fileInfoService;
    @GetMapping("/getFileInfoList")
    public Wrapper<PageInfo<FileInfo>> getFileInfoList(Page page, FileInfo fileInfo) {
        PageHelper.startPage((int)page.getCurrent(),(int)page.getSize());
        List<FileInfo> fileInfoList = fileInfoService.getFileInfoList(fileInfo);
        PageInfo<FileInfo> fileInfoPageInfo = new PageInfo<>(fileInfoList);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,fileInfoPageInfo);
    }
    @PostMapping("/fileInfoSave")
    public Wrapper<Boolean> fileInfoSave(@RequestBody FileInfo fileInfo) {
        Boolean saveResult = fileInfoService.fileInfoSave(fileInfo);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,saveResult);
    }
    @PatchMapping("/fileInfoUpdate")
    public Wrapper<Boolean> fileInfoUpdate(@RequestBody FileInfo fileInfo) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,fileInfoService.fileInfoUpdate(fileInfo));
    }
    @DeleteMapping("/fileInfoDelete")
    public Wrapper<Boolean> fileInfoDelete(FileInfo fileInfo) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,fileInfoService.fileInfoDelete(fileInfo));
    }
}
