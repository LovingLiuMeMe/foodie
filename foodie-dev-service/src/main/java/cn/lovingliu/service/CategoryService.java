package cn.lovingliu.service;

import cn.lovingliu.pojo.Category;
import cn.lovingliu.pojo.vo.CategoryVO;
import cn.lovingliu.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {
    List<Category> queryAllRootLevelCat();
    List<CategoryVO> getSubCatList(Integer rootCatId);
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
