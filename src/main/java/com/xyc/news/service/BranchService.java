package com.xyc.news.service;

import com.xyc.news.pojo.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuyuyu
 * @ClassName branchTypeService
 * @Description TODO
 * 2023/2/19  9:47
 */
@Service
public class BranchService {
    @Autowired
    com.xyc.news.mapper.BranchMapper BranchMapper;

    public List<Branch> getBranchList(Branch branch) {
        return BranchMapper.getBranchList(branch);
    }

    public Branch branchSave(Branch branch) {
        BranchMapper.insert(branch);
        return branch;
    }

    public Boolean branchUpdate(Branch branch) {
         BranchMapper.updateByPrimaryKey(branch);
         return true;
    }

    public Boolean branchDelete(Branch branch) {
        BranchMapper.deleteByPrimaryKey(branch.getBranchNo());
        return true;
    }

}
