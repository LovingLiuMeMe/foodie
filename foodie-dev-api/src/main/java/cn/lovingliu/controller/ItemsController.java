package cn.lovingliu.controller;

import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.common.enums.CommentLevel;
import cn.lovingliu.common.page.PagedGridResult;
import cn.lovingliu.pojo.Items;
import cn.lovingliu.pojo.ItemsImg;
import cn.lovingliu.pojo.ItemsParam;
import cn.lovingliu.pojo.ItemsSpec;
import cn.lovingliu.pojo.vo.CommentLevelCountsVO;
import cn.lovingliu.pojo.vo.ItemInfoVO;
import cn.lovingliu.pojo.vo.ShopcartVO;
import cn.lovingliu.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 商品Controller
 * @Date：Created in 2019-12-31
 */
@Api(value = "商品接口",tags = "商品信息展示的相关接口")
@RestController
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemService itemService;


    @ApiOperation(value = "查询商品详情",notes = "查询商品详情",httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public ServerResponse<ItemInfoVO> info(
            @ApiParam(name = "itemId",value = "商品Id",required = true)
            @PathVariable("itemId") String itemId) {
        if(StringUtils.isBlank(itemId)){
            return ServerResponse.createByErrorMessage("商品信息不存在");
        }
        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemImgList = itemService.queryItemImgList(itemId);
        ItemsParam itemParams = itemService.queryItemParam(itemId);
        List<ItemsSpec> itemSpecList = itemService.queryItemSpecList(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemImgList);
        itemInfoVO.setItemParams(itemParams);
        itemInfoVO.setItemSpecList(itemSpecList);

        return ServerResponse.createBySuccess(itemInfoVO);
    }

    @ApiOperation(value = "查询评价数量",notes = "查询评价数量",httpMethod = "GET")
    @GetMapping("/commentLevel")
    public ServerResponse<CommentLevelCountsVO> commentLevel (
            @ApiParam(name = "itemId",value = "商品Id",required = true)
            @RequestParam("itemId") String itemId) {
        if(StringUtils.isBlank(itemId)){
            return ServerResponse.createByErrorMessage("商品信息不存在");
        }

        Integer goodsCounts = itemService.queryCommentCounts(itemId, CommentLevel.GOOD.level);
        Integer normalCounts = itemService.queryCommentCounts(itemId, CommentLevel.NORMAL.level);
        Integer badCounts = itemService.queryCommentCounts(itemId, CommentLevel.BAD.level);
        Integer totalCounts = goodsCounts + normalCounts + badCounts;

        CommentLevelCountsVO commentLevelCountsVO = new CommentLevelCountsVO();
        commentLevelCountsVO.setTotalCounts(totalCounts);
        commentLevelCountsVO.setGoodCounts(goodsCounts);
        commentLevelCountsVO.setNormalCounts(normalCounts);
        commentLevelCountsVO.setBadCounts(badCounts);

        return ServerResponse.createBySuccess(commentLevelCountsVO);
    }

    @ApiOperation(value = "查询商品评论",notes = "查询商品评论",httpMethod = "GET")
    @GetMapping("/comments")
    public ServerResponse<PagedGridResult> comments (
            @ApiParam(name = "itemId",value = "商品Id",required = true)
            @RequestParam("itemId") String itemId,

            @ApiParam(name = "level",value = "评价等级",required = false)
            @RequestParam("level") Integer level,

            @ApiParam(name = "page",value = "当前页",required = false)
            @RequestParam(value = "page",defaultValue = "1") Integer page,

            @ApiParam(name = "itemId",value = "每页显示的数量",required = false)
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {

        if(StringUtils.isBlank(itemId)){
            return ServerResponse.createByErrorMessage("商品信息不存在");
        }
        PagedGridResult grid = itemService.queryPagedComments(itemId,level,page,pageSize);

        return ServerResponse.createBySuccess(grid);
    }

    @ApiOperation(value = "搜索商品列表",notes = "搜索商品列表",httpMethod = "GET")
    @GetMapping("/search")
    public ServerResponse<PagedGridResult> search (
            @ApiParam(name = "keywords",value = "搜索条件",required = false)
            @RequestParam("keywords") String keywords,

            @ApiParam(name = "sort",value = "排序条件",required = false)
            @RequestParam("sort") String sort,

            @ApiParam(name = "page",value = "当前页",required = false)
            @RequestParam(value = "page",defaultValue = "1") Integer page,

            @ApiParam(name = "itemId",value = "每页显示的数量",required = false)
            @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize) {

        PagedGridResult grid = itemService.searchItems(keywords,sort,page,pageSize);

        return ServerResponse.createBySuccess(grid);
    }

    @ApiOperation(value = "根据三级分类搜索商品",notes = "根据三级分类搜索商品",httpMethod = "GET")
    @GetMapping("/catItems")
    public ServerResponse<PagedGridResult> catItems (
            @ApiParam(name = "catId",value = "第三级分类",required = true)
            @RequestParam(value = "catId",required = true) Integer catId,

            @ApiParam(name = "sort",value = "排序条件",required = false)
            @RequestParam("sort") String sort,

            @ApiParam(name = "page",value = "当前页",required = false)
            @RequestParam(value = "page",defaultValue = "1") Integer page,

            @ApiParam(name = "itemId",value = "每页显示的数量",required = false)
            @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize) {
        if(catId == null){
            return ServerResponse.createByErrorMessage("分类不存在");
        }
        PagedGridResult grid = itemService.searchItemsByThirdCat(catId,sort,page,pageSize);

        return ServerResponse.createBySuccess(grid);
    }

    // 用于用户长时间未登录网站,刷新购物车之间的数据（主要是商品价格）
    @ApiOperation(value = "根据商品规格ids查找最新的商品数据",notes = "根据商品规格ids查找最新的商品数据",httpMethod = "GET")
    @GetMapping("/refresh")
    public ServerResponse<List<ShopcartVO>> refresh (
            @ApiParam(name = "itemSpecIds",value = "拼接的规格IDS",required = true,example = "1,2,3,5")
            @RequestParam String itemSpecIds) {
        if(StringUtils.isBlank(itemSpecIds)){
            return ServerResponse.createByErrorMessage("规格不存在");
        }
        List<ShopcartVO>  shopcartVOList = itemService.queryItemsBySpecIds(itemSpecIds);
        return ServerResponse.createBySuccess(shopcartVOList);
    }
}
