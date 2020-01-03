package cn.lovingliu.pojo.vo;

import cn.lovingliu.pojo.Items;
import cn.lovingliu.pojo.ItemsImg;
import cn.lovingliu.pojo.ItemsParam;
import cn.lovingliu.pojo.ItemsSpec;
import lombok.Data;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 商品详细信息VO
 * @Date：Created in 2020-01-02
 */
@Data
public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private ItemsParam itemParams;
    private List<ItemsSpec> itemSpecList;
}
