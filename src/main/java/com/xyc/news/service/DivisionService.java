package com.xyc.news.service;

import com.xyc.news.pojo.Division;
import com.xyc.news.pojo.vo.BranchDivisionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuyuyu
 * @ClassName divisionTypeService
 * @Description TODO
 * 2023/2/19  9:47
 */
@Service
public class DivisionService {
    @Autowired
    com.xyc.news.mapper.DivisionMapper DivisionMapper;

    public List<Division> getDivisionList(Division division) {
        return DivisionMapper.getDivisionList(division);
    }

    public Division divisionSave(Division division) {
        DivisionMapper.insert(division);
        return division;
    }

    public Boolean divisionUpdate(Division division) {
         DivisionMapper.updateByPrimaryKey(division);
         return true;
    }

    public Boolean divisionDelete(Division division) {
        DivisionMapper.deleteByPrimaryKey(division.getDivisionNo());
        return true;
    }
    public List<BranchDivisionVo> getBranchDivision(Division division) {
        List<Division>  divisions= DivisionMapper.getBranchDivision(division);
        Map<String,BranchDivisionVo> branchDivisionMap = new HashMap<String,BranchDivisionVo>();
        List<BranchDivisionVo>  branchDivisionVos = new ArrayList<BranchDivisionVo>();
        for(int i=0;i<divisions.size();i++){
            Division item = divisions.get(i);
            String key = item.getBranchNo().toString();
            if(branchDivisionMap.get(key)!=null){
                BranchDivisionVo  divisionVo= new BranchDivisionVo();
                divisionVo.setLabel(item.getDivisionName());
                divisionVo.setValue(item.getDivisionNo());
                branchDivisionMap.get(key).getChildren().add(divisionVo);
            }else{
                BranchDivisionVo  branchVo= new BranchDivisionVo();
                branchVo.setLabel(item.getBranchName());
                branchVo.setValue(item.getBranchNo());
                branchVo.setChildren(new ArrayList<>());
                BranchDivisionVo  divisionVo= new BranchDivisionVo();
                divisionVo.setLabel(item.getDivisionName());
                divisionVo.setValue(item.getDivisionNo());
                branchVo.getChildren().add(divisionVo);
                branchDivisionMap.put(item.getBranchNo().toString(),branchVo);
                branchDivisionVos.add(branchVo);
            }
        }
        return branchDivisionVos;
    }
}
