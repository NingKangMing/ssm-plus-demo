package com.wholesmart.controller.sys;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.wholesmart.common.web.JSONMessage;
import com.wholesmart.common.web.PageInfo;
import com.wholesmart.controller.BaseController;
import com.wholesmart.entity.UserRole;
import com.wholesmart.service.api.sys.IUserRoleService;

import io.swagger.annotations.ApiOperation;




/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-29
 */
@RestController
@RequestMapping("userRole")
public class UserRoleController extends BaseController{

   @Autowired
   private IUserRoleService serviceI;
    
    
    @ApiOperation(value="进入保存页面，如果传id则进入更新页面并返回一条实体数据",httpMethod="GET",notes="进入保存页面，如果传id则进入更新页面并返回一条实体数据")
    @RequestMapping("/preSave")
    public ModelAndView preSave(ModelAndView modelAndView, @RequestParam(value = "id", required = false) Long id) {
        modelAndView.setViewName("sys/userRole");
        if (id != null) {
        	UserRole userRole = serviceI.selectById(id);
        	JSONMessage message = JSONMessage.success("success", userRole);
            modelAndView.addObject("pageData", message);
        }
        return modelAndView;
    }
    
    @ApiOperation(value="保存或更新数据，对象带ID则认为更新数据",httpMethod="POST",notes="保存或更新数据")
    @ResponseBody
    @RequestMapping("save")
    public JSONMessage save(UserRole userRole) {
        if (null==userRole.getId()) {
        	boolean success = serviceI.insert(userRole);
            return JSONMessage.success("save success", success);
        } else {
        	boolean success = serviceI.updateById(userRole);
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
    @ResponseBody
    @RequestMapping("/listPage")
     public JSONMessage listPage(PageInfo pageInfo) {
    	 Page<UserRole> page = serviceI.selectPage(new Page<>(pageInfo.getCurrent(), pageInfo.getLimit()));
         JSONMessage message = JSONMessage.success("success", page);
         return message;
     }
    
}
