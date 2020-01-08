package cn.lovingliu.service;

import cn.lovingliu.common.page.PagedGridResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 基础的Service 封装公共方法
 * @Date：Created in 2020-01-08
 */
public class BaseService {
    public PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());

        return grid;
    }
}
