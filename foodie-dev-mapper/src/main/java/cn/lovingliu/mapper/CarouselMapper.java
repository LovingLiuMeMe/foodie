package cn.lovingliu.mapper;

import cn.lovingliu.pojo.Carousel;

import java.util.List;

public interface CarouselMapper {
    int deleteByPrimaryKey(String id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);

    List<Carousel> selectAllByIsShow(Integer isShow);
}