package com.xyc.news.common;

import lombok.Data;

/**
 * 页* @author xuyuyu
 * @ClassName PageVo
 * @Description TODO
 * 2023/2/13  11:08
 */
@Data
public class Page {
    protected long total;
    protected long size;
    protected long current;
}
