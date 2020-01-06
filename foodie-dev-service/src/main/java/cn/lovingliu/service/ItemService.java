package cn.lovingliu.service;

import cn.lovingliu.common.page.PagedGridResult;
import cn.lovingliu.pojo.Items;
import cn.lovingliu.pojo.ItemsImg;
import cn.lovingliu.pojo.ItemsParam;
import cn.lovingliu.pojo.ItemsSpec;
import cn.lovingliu.pojo.vo.ShopcartVO;

import java.util.List;

public interface ItemService {
    /**
     * 获得商品详细信息
     * @param itemId
     * @return
     */
    Items queryItemById(String itemId);

    /**
     * 获得商品图片的列表
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 获得商品规格列表
     * @param itemId
     * @return
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 获得商品的参数信息
     * @param itemId
     * @return
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 获得各种评价等级所对应的数量
     * @param itemId
     * @return
     */
    Integer queryCommentCounts(String itemId, Integer level);

    /**
     * 获得商品的评价列表 (分页)
     * @param itemId 商品ID
     * @param level 上屏评价的等级 1:2:3
     * @return
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 根据条件搜索商品列表
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 根据第三级分类Id获取商品信息
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult searchItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据商品规格IDS查询最新的购物车中的商品信息（用于刷新渲染购物车:防止商品在加入购物车时和当前时间的价格等信息不一致）
     * @param specIds
     * @return
     */
    List<ShopcartVO> queryItemsBySpecIds(String specIds);

    /**
     * 根据规格Id查询规格信息
     * @param itemSpecId
     * @return
     */
    ItemsSpec queryItemSpecById(String itemSpecId);

    /**
     * 根据商品Id查询主图
     * @param itemId
     * @return
     */
    ItemsImg queryItemMainImgById(String itemId);

    /**
     * 减少库存
     * @param itemSpecId
     * @param buyCounts
     * @return
     */
    Integer decreaseItemSpecStock(String itemSpecId, Integer buyCounts);

}
