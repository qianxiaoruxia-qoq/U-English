package com.ue.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  分页工具类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    // 当前页
    private Integer currentPage;
    // 数据总条数
    private Integer total;
    private List<T> object;
    // 总页数
    private Integer pageCount;

    public Integer getPageCount() {
        if (pageCount == null) {
            return 0;
        } else {
            return pageCount;
        }
    }
}
