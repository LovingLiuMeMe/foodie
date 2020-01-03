package cn.lovingliu.service.impl;

import cn.lovingliu.mapper.CarouselMapper;
import cn.lovingliu.pojo.Carousel;
import cn.lovingliu.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 轮播图
 * @Date：Created in 2019-12-31
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;
    /**
     * 查询所有轮播图列表
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(Integer isShow) {
        List<Carousel> carouselList = carouselMapper.selectAllByIsShow(isShow);

        return carouselList;
    }
}
