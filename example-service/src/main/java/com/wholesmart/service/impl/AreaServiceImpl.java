package com.wholesmart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wholesmart.entity.Area;
import com.wholesmart.mapper.AreaMapper;
import com.wholesmart.service.IAreaService;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-24
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {
	@Autowired
	private AreaMapper AreaMapper;
	
	@Override
	public Page<Area> selectAreaPage(Page<Area> page, Wrapper<Area> wrapper) {
		page.setRecords(baseMapper.selectAreaPage(page, wrapper));
		return page;
	}
	
}
