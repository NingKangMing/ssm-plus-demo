package com.wholesmart.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wholesmart.entity.User;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-22
 */
public interface UserMapper extends BaseMapper<User> {
	
	User selectUserRole(Map<String, Object> parameter);
	
	/**
     * <p>
     * 查询 : 根据模糊字段查询用户列表，分页显示
     * </p>
     *
     * @param page
     *            翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @param state
     *            状态
     * @return
     */
    List<User> selectUserPage(RowBounds rowBounds, @Param("ew") Wrapper<User> wrapper);

}