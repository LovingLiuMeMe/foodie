package cn.lovingliu.service.impl;

import cn.lovingliu.common.enums.CategoryLevel;
import cn.lovingliu.mapper.CategoryMapper;
import cn.lovingliu.mapper.CategoryMapperCustom;
import cn.lovingliu.pojo.Category;
import cn.lovingliu.pojo.vo.CategoryVO;
import cn.lovingliu.pojo.vo.NewItemsVO;
import cn.lovingliu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：LovingLiu
 * @Description: 主页分类相关业务代码
 * @Date：Created in 2019-12-31
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;
    /**
     * 查询所有1级分类
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryAllRootLevelCat(){
        List<Category> categoryList = categoryMapper.selectAllByType(CategoryLevel.LEVEL1.type);
        return categoryList;
    }

    /**
     * 根据一级分类ID查询子分类信息
     * @param rootCatId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("rootCatId",rootCatId);
        return categoryMapperCustom.getSixNewItemsLazy(paramsMap);
    }
}
