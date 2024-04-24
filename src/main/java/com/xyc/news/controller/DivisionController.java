package com.xyc.news.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.news.common.Page;
import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import com.xyc.news.pojo.Division;
import com.xyc.news.pojo.vo.BranchDivisionVo;
import com.xyc.news.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分部控制器* @author xuYuYu
 * @date 2024-04-23 10:04:48
 */
@CrossOrigin
@RestController
@RequestMapping("/division")
public class DivisionController {
    @Autowired
    private DivisionService divisionService;
    @GetMapping("/getDivisionList")
    public Wrapper<PageInfo<Division>> getDivisionList(Page page, Division division) {
        PageHelper.startPage((int)page.getCurrent(),(int)page.getSize());
        List<Division> divisionList = divisionService.getDivisionList(division);
        PageInfo<Division> divisionPageInfo = new PageInfo<>(divisionList);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,divisionPageInfo);
    }
    @GetMapping("/getBranchDivision")
    public Wrapper<List<BranchDivisionVo>> getBranchDivision(Division division) {
        List<BranchDivisionVo> branchDivisionVos = divisionService.getBranchDivision(division);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,branchDivisionVos);
    }
    @PostMapping("/divisionSave")
    public Wrapper<Division> divisionSave(@RequestBody Division division) {
        Division divisionResult = divisionService.divisionSave(division);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,divisionResult);
    }
    @PostMapping("/divisionUpdate")
    public Wrapper<Boolean> divisionUpdate(@RequestBody Division division) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,divisionService.divisionUpdate(division));
    }
    @GetMapping("/divisionDelete")
    public Wrapper<Boolean> divisionDelete(Division division) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,divisionService.divisionDelete(division));
    }
}
