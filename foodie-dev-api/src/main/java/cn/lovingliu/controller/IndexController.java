package cn.lovingliu.controller;

import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.common.enums.YesOrNo;
import cn.lovingliu.pojo.Carousel;
import cn.lovingliu.pojo.Category;
import cn.lovingliu.pojo.vo.CategoryVO;
import cn.lovingliu.pojo.vo.NewItemsVO;
import cn.lovingliu.service.CarouselService;
import cn.lovingliu.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 首页Controller
 * @Date：Created in 2019-12-31
 */
@Api(value = "首页",tags = "首页展示的相关接口")
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "用户轮播图",notes = "用户轮播图",httpMethod = "GET")
    @GetMapping("/carousel")
    public ServerResponse<List<Carousel>> carousel() {
        List<Carousel> carouselList = carouselService.queryAll(YesOrNo.YES.type);
        return ServerResponse.createBySuccess(carouselList);
    }

    /**
     * 首页分类展示需求
     * 1.第一次刷新主页主页查询一级分类,渲染展示到页面
     * 2.如果鼠标上移到一级,则加载二级分类的内容,如果已经存在子分类,则不需要加载(懒加载)
     */
    @ApiOperation(value = "获取商品分类(1级)",notes = "获取商品分类(1级)",httpMethod = "GET")
    @GetMapping("/cats")
    public ServerResponse<List<Category>> cats() {
        List<Category> categoryList = categoryService.queryAllRootLevelCat();
        return ServerResponse.createBySuccess(categoryList);
    }

    @ApiOperation(value = "获取商品子分类",notes = "获取商品子分类",httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public ServerResponse<List<CategoryVO>> subCat(
            @ApiParam(name = "rootCatId",value = "一级分类ID",required = true)
            @PathVariable("rootCatId") Integer rootCatId) {
        if (rootCatId == null){
            return ServerResponse.createByErrorMessage("分类不存在");
        }
        List<CategoryVO> categoryVOList = categoryService.getSubCatList(rootCatId);
        return ServerResponse.createBySuccess(categoryVOList);
    }

    @ApiOperation(value = "查询一级分类下的6个最新商品信息",notes = "查询一级分类下的6个最新商品信息",httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public ServerResponse<List<NewItemsVO>> sixNewItems(
            @ApiParam(name = "rootCatId",value = "一级分类ID",required = true)
            @PathVariable("rootCatId") Integer rootCatId) {
        if (rootCatId == null){
            return ServerResponse.createByErrorMessage("分类不存在");
        }
        List<NewItemsVO> newItemsVOList = categoryService.getSixNewItemsLazy(rootCatId);
        return ServerResponse.createBySuccess(newItemsVOList);
    }
}
