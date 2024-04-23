package com.xyc.news.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.news.common.Page;
import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import com.xyc.news.pojo.Branch;
import com.xyc.news.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分支控制器*
 * @author xuyuyu
 * @Description TODO
 * @date 2024-04-23 10:07:52
 */
@CrossOrigin
@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;
    @GetMapping("/getBranchList")
    public Wrapper<PageInfo<Branch>> getBranchList(Page page, Branch branch) {
        PageHelper.startPage((int)page.getCurrent(),(int)page.getSize());
        List<Branch> branchList = branchService.getBranchList(branch);
        PageInfo<Branch> branchPageInfo = new PageInfo<>(branchList);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,branchPageInfo);
    }
    @GetMapping("/getAllBranch")
    public Wrapper<List<Branch>> getAllBranch(Branch branch) {
        List<Branch> branchList = branchService.getBranchList(branch);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,branchList);
    }
    @PostMapping("/branchSave")
    public Wrapper<Branch> branchSave(@RequestBody Branch branch) {
        Branch branchResult = branchService.branchSave(branch);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,branchResult);
    }
    @PostMapping("/branchUpdate")
    public Wrapper<Boolean> branchUpdate(@RequestBody Branch branch) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,branchService.branchUpdate(branch));
    }
    @GetMapping("/branchDelete")
    public Wrapper<Boolean> branchDelete(Branch branch) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,branchService.branchDelete(branch));
    }
}
