package com.xyc.news.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import com.xyc.news.common.Page;
import com.xyc.news.pojo.FileType;
import com.xyc.news.service.FileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件类型控制器* @author xuyuyu
 * @ClassName FileTypeController
 * @Description TODO
 * @date 2024-04-23 10:08:23
 */
@RestController
@CrossOrigin
@RequestMapping("/fileType")
public class FileTypeController {
    @Autowired
    FileTypeService fileTypeService;
    @GetMapping("/getFileTypeList")
    public Wrapper<PageInfo<FileType>> getFileTypeList(Page page, FileType fileType) {
        PageHelper.startPage((int)page.getCurrent(),(int)page.getSize());
        List<FileType> fileTypeList = fileTypeService.getFileTypeList(fileType);
        PageInfo<FileType> fileTypePageInfo = new PageInfo<>(fileTypeList);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,fileTypePageInfo);
    }
    @GetMapping("/getAllFileType")
    public Wrapper<List<FileType> > getAllFileType() {
        List<FileType> fileTypeList = fileTypeService.getFileTypeList(new FileType());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, fileTypeList);
    }
    @PostMapping("/fileTypeSave")
    public Wrapper<Boolean> fileTypeSave(@RequestBody FileType fileType) {
        Boolean saveResult = fileTypeService.fileTypeSave(fileType);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,saveResult);
    }
    @PatchMapping("/fileTypeUpdate")
    public Wrapper<Boolean> fileTypeUpdate(@RequestBody FileType fileType) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, fileTypeService.fileTypeUpdate(fileType));
    }
    @DeleteMapping("/fileTypeDelete")
    public Wrapper<Boolean> fileTypeDelete(FileType fileType) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, fileTypeService.fileTypeDelete(fileType));
    }
}
