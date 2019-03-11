package com.wholesmart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wholesmart.entity.Area;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-24
 */
public interface AreaMapper extends BaseMapper<Area> {
	List<Area> selectAreaPage(RowBounds rowBounds, @Param("ew") Wrapper<Area> wrapper);
}