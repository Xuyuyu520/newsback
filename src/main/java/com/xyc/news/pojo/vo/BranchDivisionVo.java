package com.xyc.news.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author xuyuyu
 * @ClassName BranchDivisionVo
 * @Description TODO
 * 2024/1/11  21:29
 */
@Data
public class BranchDivisionVo {
    private Integer  value;
    private String   label;
    private List<BranchDivisionVo> children;
}
