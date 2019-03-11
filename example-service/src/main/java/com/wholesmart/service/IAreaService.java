package com.wholesmart.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wholesmart.entity.Area;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-24
 */
public interface IAreaService extends IService<Area> {
	Page<Area> selectAreaPage(Page<Area> page, Wrapper<Area> wrapper);
}
