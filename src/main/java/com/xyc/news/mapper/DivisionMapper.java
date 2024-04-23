package com.xyc.news.mapper;

import com.xyc.news.pojo.Division;

import java.util.List;

public interface DivisionMapper {
    int deleteByPrimaryKey(Integer divisionNo);

    int insert(Division record);

    int insertSelective(Division record);

    Division selectByPrimaryKey(Integer divisionNo);

    int updateByPrimaryKeySelective(Division record);

    int updateByPrimaryKey(Division record);

    List<Division> getDivisionList(Division division);

    List<Division> getBranchDivision(Division division);
}
