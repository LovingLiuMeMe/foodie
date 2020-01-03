package cn.lovingliu.mapper;

import cn.lovingliu.pojo.vo.CategoryVO;
import cn.lovingliu.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 自定义Mapper文件
 */
public interface CategoryMapperCustom {
    List<CategoryVO> getSubCatList(Integer rootCatId);
    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String,Object> paramsMap);
}