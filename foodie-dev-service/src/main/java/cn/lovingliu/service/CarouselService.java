package cn.lovingliu.service;

import cn.lovingliu.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    List<Carousel> queryAll(Integer isShow);
}
