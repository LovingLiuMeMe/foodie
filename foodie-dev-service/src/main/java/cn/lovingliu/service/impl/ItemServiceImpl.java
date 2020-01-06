package cn.lovingliu.service.impl;

import cn.lovingliu.common.enums.YesOrNo;
import cn.lovingliu.common.page.PagedGridResult;
import cn.lovingliu.common.util.DesensitizationUtil;
import cn.lovingliu.mapper.*;
import cn.lovingliu.pojo.Items;
import cn.lovingliu.pojo.ItemsImg;
import cn.lovingliu.pojo.ItemsParam;
import cn.lovingliu.pojo.ItemsSpec;
import cn.lovingliu.pojo.vo.ItemCommentVO;
import cn.lovingliu.pojo.vo.SearchItemsVO;
import cn.lovingliu.pojo.vo.ShopcartVO;
import cn.lovingliu.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @Author：LovingLiu
 * @Description: 商品详情
 * @Date：Created in 2019-12-31
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        return itemsImgMapper.selectByItemId(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        return itemsSpecMapper.selectByItemId(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        return itemsParamMapper.selectByItemId(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryCommentCounts(String itemId, Integer level) {
        return  itemsCommentsMapper.selectByItemIdAndCommentLevel(itemId, level);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(String itemId,
                                                  Integer level,
                                                  Integer page,
                                                  Integer pageSize) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("itemId",itemId);
        paramsMap.put("level",level);
        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> list = itemsMapperCustom.queryItemComments(paramsMap);
        /**
         * 脱敏操作
         */
        for (ItemCommentVO vo : list) {
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
        }

        return setterPagedGrid(list,page);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItems(String keywords,
                                       String sort,
                                       Integer page,
                                       Integer pageSize) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("keywords",keywords);
        paramsMap.put("sort",sort);
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> list = itemsMapperCustom.searchItems(paramsMap);
        return setterPagedGrid(list,page);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItemsByThirdCat(Integer catId,
                                       String sort,
                                       Integer page,
                                       Integer pageSize) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("catId",catId);
        paramsMap.put("sort",sort);
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> list = itemsMapperCustom.searchItemsByThirdCat(paramsMap);
        return setterPagedGrid(list,page);
    }
    private PagedGridResult setterPagedGrid(List<?> list,Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());

        return grid;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopcartVO> queryItemsBySpecIds(String specIds) {
        String ids[] = StringUtils.split(specIds,",");
        List<String> specIdList = new ArrayList<>();
        Collections.addAll(specIdList, ids);
        return itemsMapperCustom.queryItemsBySpecIds(specIdList);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsSpec queryItemSpecById(String itemSpecId) {
        return itemsSpecMapper.selectByPrimaryKey(itemSpecId);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsImg queryItemMainImgById(String itemId) {
        Map<String,Object> parmasMap = new HashMap<>();
        parmasMap.put("itemId",itemId);
        parmasMap.put("isMain",YesOrNo.YES.type);
        return itemsImgMapper.selectByItemIdAndIsMain(parmasMap);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer decreaseItemSpecStock(String itemSpecId, Integer buyCounts) {
        /**
         * 防止超卖问题:
         * 1.synchronized 不推荐,性能低下,集群下无用
         * 2.数据库锁:不推荐,数据库性能低下
         * 3.分布式锁 zookeeper redis
         */
        Map<String,Object> parmasMap = new HashMap<>();
        parmasMap.put("decreaseStock",buyCounts);
        parmasMap.put("specId",itemSpecId);
        int count =  itemsMapperCustom.decreaseItemSpecStock(parmasMap);
        if(count != 1) {
            throw new RuntimeException("订单创建失败,原因库存扣除失败!");
        }
        return count;
    }
}
