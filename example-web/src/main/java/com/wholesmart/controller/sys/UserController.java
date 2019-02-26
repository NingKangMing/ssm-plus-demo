package com.wholesmart.controller.sys;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wholesmart.common.web.JSONMessage;
import com.wholesmart.entity.User;
import com.wholesmart.service.IUserService;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-22
 */
@RestController
@RequestMapping("user")
public class UserController {

   @Autowired
   private IUserService serviceI;
   
   @ApiOperation(value="得到数据列表",httpMethod="GET",notes="得到数据列表")
   @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView) {
        List<User> list = serviceI.selectList(null);
        modelAndView.setViewName("sys/user");
        JSONMessage message = JSONMessage.success("success", list);
        modelAndView.addObject("pageData", message);
        return modelAndView;
    }

    @ApiOperation(value="进入保存页面，如果传id则进入更新页面并返回一条实体数据",httpMethod="GET",notes="进入保存页面，如果传id则进入更新页面并返回一条实体数据")
    @RequestMapping("/preSave")
    public ModelAndView preSave(ModelAndView modelAndView, @RequestParam(value = "id", required = false) Long id) {
        modelAndView.setViewName("sys/save");
        if (id != null) {
        	User user = serviceI.selectById(id);
        	JSONMessage message = JSONMessage.success("success", user);
            modelAndView.addObject("pageData", message);
        }
        return modelAndView;
    }

    @ApiOperation(value="保存或更新数据，对象带ID则认为更新数据",httpMethod="POST",notes="保存或更新数据")
    @ResponseBody
    @RequestMapping("save")
    public JSONMessage save(User user) {
        if (null==user.getId()) {
        	//user.insert();//开挂模式
        	boolean success = serviceI.insert(user);
            return JSONMessage.success("save success", success);
        } else {
        	boolean success = serviceI.updateById(user);
            return JSONMessage.success("update success", success);
        }
    } 

    @ApiOperation(value="删除一条数据",httpMethod="GET",notes="删除一条数据")
    @ResponseBody
    @RequestMapping("/delete")
    public JSONMessage delete(@RequestParam(value = "id", required = false) Long id) {
    	boolean b = serviceI.deleteById(id);
        return JSONMessage.success("delete success",b);
    }
    
    @ApiOperation(value="得到分页数据列表,需要分页参数current和limit",httpMethod="GET",notes="得到分页数据列表,需要分页参数current和limit")
    @RequestMapping("/listPage")
     public JSONMessage listPage(Pagination pageInfo) {
    	 Page<User> page = serviceI.selectPage(new Page<>(pageInfo.getCurrent(), pageInfo.getLimit()));
         JSONMessage message = JSONMessage.success("success", page);
         return message;
     }
}
