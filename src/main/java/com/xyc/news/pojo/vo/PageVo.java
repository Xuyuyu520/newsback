package com.xyc.news.pojo.vo;
import lombok.Data;

/**
 * @author xuyuyu
 * @ClassName PageVo
 * @Description TODO
 * 2023/2/13  11:08
 */
@Data
public class PageVo {
    protected long total;
    protected long size;
    protected long current;
}
