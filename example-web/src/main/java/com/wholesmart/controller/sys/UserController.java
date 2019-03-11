package com.wholesmart.controller.sys;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.Validator;
import com.wholesmart.common.annotation.ApiLog;
import com.wholesmart.common.shiro.entity.ShiroUser;
import com.wholesmart.common.shiro.util.ShiroCryptosUtil;
import com.wholesmart.common.util.ExportExcel;
import com.wholesmart.common.util.ImportExcelJXL;
import com.wholesmart.common.util.StringUtils;
import com.wholesmart.common.web.JSONMessage;
import com.wholesmart.common.web.PageInfo;
import com.wholesmart.controller.BaseController;
import com.wholesmart.entity.Role;
import com.wholesmart.entity.User;
import com.wholesmart.service.api.sys.IRoleService;
import com.wholesmart.service.api.sys.IUserService;

import io.swagger.annotations.ApiOperation;

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
public class UserController extends BaseController{
	
	private String[] Title = {"账号","密码","年龄","姓名",
			"手机号","身份证","部门表id","性别","email","职务","注册IP",
			"头像地址","录入人","录入时间","数据更新时间","状态","是否锁定","部门名称"};
	private Map<String, String> dataTitlle;
	
	public UserController() {
		dataTitlle= new HashMap<String, String>();
    	dataTitlle.put("账号", "username");
    	dataTitlle.put("密码", "password");
    	dataTitlle.put("年龄", "age");
    	dataTitlle.put("姓名", "relname");
    	dataTitlle.put("手机号", "phone");
    	dataTitlle.put("身份证", "idcard");
    	dataTitlle.put("部门表id", "departmentId");
    	dataTitlle.put("性别", "sex");
     	dataTitlle.put("email", "email");
    	dataTitlle.put("职务", "duty");
    	dataTitlle.put("注册IP", "regIp");
    	dataTitlle.put("头像地址", "avatar");
    	dataTitlle.put("录入人", "inputUser");
    	dataTitlle.put("录入时间", "createTime");
    	dataTitlle.put("数据更新时间", "updateTime");
    	dataTitlle.put("状态", "status");
    	dataTitlle.put("是否锁定", "uLocked");
    	dataTitlle.put("部门名称", "department");
	}

	@Autowired
	private IUserService serviceI;

	@Autowired
	private IRoleService roleservice;

	@ApiLog(value="得到数据列表",module="用户管理",operationType="查询")
	@ApiOperation(value="得到数据列表",httpMethod="GET",notes="得到数据列表")
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView modelAndView) {
		List<User> list = serviceI.selectList(new EntityWrapper<User>().eq("status", "0"));
		modelAndView.setViewName("user/user");
		JSONMessage message = JSONMessage.success("success", list);
		modelAndView.addObject("pageData", message);
		return modelAndView;
	}

	@ApiOperation(value="进入保存页面，如果传id则进入更新页面并返回一条实体数据。总是返回roles数据列表",httpMethod="GET",notes="进入保存页面，如果传id则进入更新页面并返回一条实体数据")
	@RequestMapping("/preSave")
	public ModelAndView preSave(ModelAndView modelAndView, @RequestParam(value = "id", required = false) Long id) {
		Map<String,Object> map=new HashMap<>();
		if(id==null) {
			modelAndView.setViewName("user/save");
		}else {
			modelAndView.setViewName("user/edit");
			User user = serviceI.selectById(id);
			map.put("user", user);
		}
		List<Role> roles = roleservice.selectList(null);
		map.put("roles", roles);
		modelAndView.addObject("pageData", JSONMessage.success("success", map));
		return modelAndView;
	}
	
	@ApiOperation(value="返回roles数据列表和用户信息",httpMethod="GET",notes="返回roles数据列表和用户信息,id必须传")
	@ResponseBody
	@RequestMapping("/getUserAndRole")
	public JSONMessage preSave(@RequestParam(value = "id", required = false) Long id) {
		Map<String,Object> map=new HashMap<>();
		User user = new User();
		if(id!=null) {
			user = serviceI.selectById(id);
		}
		map.put("user", user);
		List<Role> roles = roleservice.selectList(null);
		map.put("roles", roles);
		JSONMessage message = JSONMessage.success("success", map);
		return message;
	}

	@ApiLog(value="编辑用户数据",module="用户管理",operationType="编辑")
	@ApiOperation(value="保存或更新数据，对象带ID则认为更新数据,如果密码不为空将更新密码",httpMethod="POST",notes="保存或更新数据")
	@ResponseBody
	@RequestMapping("save")
	public JSONMessage save(User user) {
		//bean验证 可考虑用专门的验证模块来实现
		if(null==user) return JSONMessage.failure("user 对象为null");
		if(StringUtils.isNullOrBlank(user.getUsername())) return JSONMessage.failure("用户名不能为空");
		if(StringUtils.isNullOrBlank(user.getPassword())) return JSONMessage.failure("密码不能为空");
		if (null==user.getId()) {
			//user.insert();//开挂模式
			//先看看是否有同名账号，有则不允许
			Wrapper<User> wrapper = new EntityWrapper<>();
			wrapper.eq("username", user.getUsername());
			User user1=serviceI.selectOne(wrapper);
			if(null!=user1) return JSONMessage.failure("账号已存在！");
			ShiroUser saltUser = ShiroCryptosUtil.md5Password(user.getUsername(), user.getPassword(), 2);
			user.setCredentialsSalt(saltUser.getCredentialsSalt());//保存密码盐
			user.setPassword(saltUser.getPassword());//用盐进行加密的密码
			boolean success = serviceI.insert(user);
			return JSONMessage.success("save success", success);
		} else {
			if(user.getPassword()!=null) {//更改密码
				ShiroUser saltUser = ShiroCryptosUtil.md5Password(user.getUsername(), user.getPassword(), 2);
				user.setCredentialsSalt(saltUser.getCredentialsSalt());//保存密码盐
				user.setPassword(saltUser.getPassword());//用盐进行加密的密码
			}
			user.setUpdateTime(new Date(System.currentTimeMillis()));
			boolean success = serviceI.updateById(user);
			return JSONMessage.success("update success", success);
		}
	} 

	@ApiLog(value="删除用户数据",module="用户管理",operationType="删除")
	@ApiOperation(value="删除一条数据",httpMethod="GET",notes="删除一条数据")
	@ResponseBody
	@RequestMapping("/delete")
	public JSONMessage delete(@RequestParam(value = "id", required = false) Long id) {
		boolean b = serviceI.deleteById(id);
		if(b) {
			return JSONMessage.success("delete success",b);
		}
		return JSONMessage.failure("delete failure");
	}
	
	@ApiLog(value="批量删除数据",module="用户管理",operationType="删除")
	@ApiOperation(value="批量删除数据",httpMethod="GET",notes="批量删除数据")
	@RequestMapping("/deleteBatch")
	public JSONMessage deleteBatch(@RequestParam(value="ids[]",required=true) List<Long> ids) {
		
		if(Validator.isNullOrEmpty(ids)) return JSONMessage.failure("ids is empty!");
		
		boolean b = serviceI.deleteBatchIds(ids);
		if(b) {
			return JSONMessage.success("delete success",b);
		}
		return JSONMessage.failure("delete failure");
	}
	
	@ApiOperation(value="根据ID获取一条记录",httpMethod="GET",notes="根据ID获取一条记录")
	@RequestMapping("/getRecord")
	public JSONMessage getRecord(Long id) {
		if(Validator.isNullOrEmpty(id)) return JSONMessage.failure("id is empty!");
		User user = serviceI.selectById(id);
		if(Validator.isNullOrEmpty(user)) {
			return JSONMessage.success("success",user);
		}
		return JSONMessage.failure("failure");
	}

	@ApiOperation(value="得到分页数据列表,需要分页参数current和limit",httpMethod="GET",
	notes="得到分页数据列表,需要分页参数current和limit,模糊查询用户目前有效参数[username,relname,idcard,duty, phone,departmentId]")
	@ResponseBody
	@RequestMapping("/listPage")
	public JSONMessage listPage(PageInfo pageInfo,User user) {
		//条件构造器
		Wrapper<User> eq = new EntityWrapper<User>().eq("status", "0");
		if(null!=user) {
			if(!StringUtils.isBlank(user.getUsername())) eq.like("username", user.getUsername());
			if(!StringUtils.isBlank(user.getRelname())) eq.like("relname", user.getRelname());
			if(!StringUtils.isBlank(user.getIdcard())) eq.like("idcard", user.getIdcard());
			if(!StringUtils.isBlank(user.getDuty())) eq.like("duty", user.getDuty());
			if(!StringUtils.isBlank(user.getPhone())) eq.like("phone", user.getPhone());
			if(null!=user.getDepartmentId()) eq.eq("department_id", user.getDepartmentId());
		}
		Page<User> page = serviceI.selectUserPage(new Page<>(pageInfo.getCurrent(), pageInfo.getLimit()),eq);
		JSONMessage message = JSONMessage.success("success", page);
		return message;
	}
	
    @ApiOperation(value="导出excel表",httpMethod="GET",notes="导出excel表")
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {    	    	
    	List<User> list = serviceI.selectList(null);
    	ExportExcel.exportExcel("用户管理列表.xls", list, response, dataTitlle, Title);
	}
    
    @ApiOperation(value="导出excel模板",httpMethod="GET",notes="导出excel模板")
  	@RequestMapping("/templet")
  	public void templet(HttpServletRequest request, HttpServletResponse response) throws Exception {
      	Map<String, Object> params = getAllParams(request);
      	List<User> list = serviceI.selectByMap(params);
      	ExportExcel.exportTempletExcel("用户管理模板.xls", Title, response);
  	}
    
    @ApiOperation(value="excel表数据批量导入",httpMethod="POST",notes="excel表数据批量导入")
  	@RequestMapping("/importExcel")
  	public JSONMessage importExcel(@RequestParam(value = "uploadfile") MultipartFile casesFile) throws Exception {
      	JSONMessage message = null;
      	List<User> list= null;
      	if(casesFile!=null && !casesFile.isEmpty()) {
      		try {
      			list = ImportExcelJXL.importExcelData(casesFile,User.class,dataTitlle,Title);
          		boolean ret = serviceI.insertBatch(list);
         		 message = JSONMessage.success("success", ret);
			} catch (Exception e) {
				 message = JSONMessage.success("success", "导入失败:请检测表格内容格式是否正确");
			}    		
      	}else {
      		message = JSONMessage.failure("上传文件为空");
      	}
      	
      	return message;
  		
  	}
}
