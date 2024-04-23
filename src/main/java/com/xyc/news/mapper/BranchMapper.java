package com.xyc.news.mapper;

import com.xyc.news.pojo.Branch;

import java.util.List;

public interface BranchMapper {
    int deleteByPrimaryKey(Integer branchNo);

    int insert(Branch record);

    int insertSelective(Branch record);

    Branch selectByPrimaryKey(Integer branchNo);

    int updateByPrimaryKeySelective(Branch record);

    int updateByPrimaryKey(Branch record);

    List<Branch> getBranchList(Branch branch);
}
