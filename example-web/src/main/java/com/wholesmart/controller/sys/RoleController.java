package com.wholesmart.controller.sys;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.bean.ConvertUtil;
import com.wholesmart.commom.tree.TreeEntity;
import com.wholesmart.commom.tree.TreeUtil;
import com.wholesmart.common.util.ExportExcel;
import com.wholesmart.common.util.ImportExcelJXL;
import com.wholesmart.common.util.StringUtils;
import com.wholesmart.common.web.JSONMessage;
import com.wholesmart.common.web.PageInfo;
import com.wholesmart.controller.BaseController;
import com.wholesmart.entity.Resource;
import com.wholesmart.entity.Role;
import com.wholesmart.entity.UserRole;
import com.wholesmart.service.api.sys.IResourceService;
import com.wholesmart.service.api.sys.IRoleService;
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
@RequestMapping("role")
public class RoleController extends BaseController{
	
	private String[] Title = {"角色名称","角色key","角色状态","角色描述",
			"创建时间","更新时间"};
	private Map<String, String> dataTitlle;

   @Autowired
   private IRoleService serviceI;
   
   @Autowired
   private IResourceService resourceService;
   
   @Autowired
   private IUserRoleService userRoleService;
   
   
   public RoleController() {
	dataTitlle= new HashMap<String, String>();
   	dataTitlle.put("角色名称", "roleName");
   	dataTitlle.put("角色key", "roleKey");
   	dataTitlle.put("角色状态", "status");
   	dataTitlle.put("角色描述", "description");
   	dataTitlle.put("创建时间", "createTime");
   	dataTitlle.put("更新时间", "updateTime");
   }
   
   @ApiOperation(value="得到分页数据列表,需要分页参数current和limit",httpMethod="GET",notes="得到分页数据列表,需要分页参数current和limit，返回页面视图")
   @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView,PageInfo pageInfo) {
   	 Page<Role> page = serviceI.selectPage(
   			 new Page<>(pageInfo.getCurrent(), pageInfo.getLimit()),
   			 new EntityWrapper<Role>().eq("status", "0"));
        JSONMessage message = JSONMessage.success("success", page);
        modelAndView.setViewName("role/role");
        modelAndView.addObject("pageData",message);
        return modelAndView;
    }
    
    
    @ApiOperation(value="进入保存页面，如果传id则进入更新页面并返回一条实体数据",httpMethod="GET",notes="进入保存页面，如果传id则进入更新页面并返回一条实体数据")
    @RequestMapping("/preSave")
    public ModelAndView preSave(ModelAndView modelAndView, @RequestParam(value = "id", required = false) Long id) {
        
        if (id != null) {
        	Role role = serviceI.selectById(id);
        	JSONMessage message = JSONMessage.success("success", role);
        	modelAndView.setViewName("role/edit");
            modelAndView.addObject("pageData", message);
        }else {
        	modelAndView.setViewName("role/save");
        }
        return modelAndView;
    }
    
    @ApiOperation(value="根据角色id返回role对象",httpMethod="GET",notes="根据角色id返回role对象")
    @ResponseBody
	@RequestMapping("/get")
    public JSONMessage preSave(@RequestParam(value = "id", required = false) Long id) {
    	Role role = serviceI.selectById(id);
    	JSONMessage message = JSONMessage.success("success", role);
		return message;
    	
    }
    
    @ApiOperation(value="保存或更新数据，对象带ID则认为更新数据",httpMethod="POST",notes="保存或更新数据")
    @ResponseBody
    @RequestMapping("save")
    public JSONMessage save(Role role) {
        if (null==role.getId()) {
        	role.setStatus(0);
        	role.setCreateTime(new Date(System.currentTimeMillis()));
        	boolean success = serviceI.insert(role);
            return JSONMessage.success("save success", success);
        } else {
        	role.setUpdateTime(new Date(System.currentTimeMillis()));
        	boolean success = serviceI.updateById(role);
            return JSONMessage.success("update success", success);
        }
    } 

    @ApiOperation(value="删除一条数据",httpMethod="GET",notes="删除一条数据")
    @ResponseBody
    @RequestMapping("/delete")
    public JSONMessage delete(@RequestParam(value = "id", required = false) Long id) {
    	//先看看有没用户引用 有的话 提示不可以删除
    	List<UserRole> list = userRoleService.selectByMap(ConvertUtil.toMap("role_id",id));
    	if(list!=null&list.size()>0) return JSONMessage.failure("角色存在引用，不能进行删除操作");
    	boolean b = serviceI.deleteById(id);
        return JSONMessage.success("delete success",b);
    }
    
    @ApiOperation(value="得到分页数据列表,需要分页参数current和limit",httpMethod="GET",notes="得到分页数据列表,需要分页参数current和limit")
    @ResponseBody
    @RequestMapping("/listPage")
     public JSONMessage listPage(PageInfo pageInfo,
    		 @RequestParam(value = "role_name", required = false) String role_name,
    		 @RequestParam(value = "role_key", required = false) String role_key) {
    	Wrapper<Role> ew = new EntityWrapper<Role>().eq("status", "0");
    	if(!StringUtils.isNullOrBlank(role_name)) {
    		ew.like("role_name", role_name);
    	}
    	if(!StringUtils.isNullOrBlank(role_key)) {
    		ew.like("role_key", role_key);
    	}
    	 Page<Role> page = serviceI.selectPage(
    			 new Page<>(pageInfo.getCurrent(), pageInfo.getLimit()),
    			 ew);
         JSONMessage message = JSONMessage.success("success", page);
         return message;
     }
    
    @ApiOperation(value="得到当前角色的权限资源信息",httpMethod="GET",notes="得到当前角色的权限资源信息,返回的对象有三个对象信息，一个角色对象，一个资源列表，一个已经解析好的用于ztree的树对象")
    @ResponseBody
    @GetMapping("{roleId}/getPermission")
    public JSONMessage getPermission(@PathVariable(required=true) Long roleId) {	
    	Map<String,Object> map=new HashMap<>();
    	Role role = serviceI.selectById(roleId);
		List<Resource> resources = resourceService.queryResourceList(ConvertUtil.toMap("hidden",(Object)0,"roleId",(Object)roleId));
		List<TreeEntity> treeResources = new TreeUtil().generateTree(resources);
		map.put("role", role);
		map.put("resources", resources);
		map.put("treeResources", treeResources);
		JSONMessage message = JSONMessage.success("success", map);
		return message;
    }
    
    @ApiOperation(value="得到当前角色的权限资源信息，返回页面视图",httpMethod="GET",notes="得到当前角色的权限资源信息")
	@GetMapping("{roleId}/permission")
    public String permission(Map<String,Object> map,@PathVariable(required=true) Long roleId) {	
		Role role = serviceI.selectById(roleId);
		List<Resource> resources = resourceService.queryResourceList(ConvertUtil.toMap("hidden",(Object)0,"roleId",(Object)roleId));
		List<TreeEntity> treeResources = new TreeUtil().generateTree(resources);
		map.put("role", role);
		map.put("resources", treeResources);
		//JSONMessage message = JSONMessage.success("success", map);
        return "role/permission";
    }
	
    @ApiOperation(value="保存当前角色的权限资源信息",httpMethod="POST",notes="保存当前角色的权限资源信息，需要参数角色id，资源id列表")
    @ResponseBody
    @PostMapping("savePermission")
	public JSONMessage permission(@RequestParam("roleId")Long roleId, @RequestParam("resourceIds[]") List<Long> resourceIds){
		boolean b = serviceI.savePermission(roleId,resourceIds);
		if(b) {
			return JSONMessage.success("save ok",b);
		} 
		return JSONMessage.failure("保存出错!");
	}
    
    @ApiOperation(value="导出excel表",httpMethod="GET",notes="导出excel表")
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {    	    	
    	List<Role> list = serviceI.selectList(null);
    	ExportExcel.exportExcel("角色管理列表.xls", list, response, dataTitlle, Title);
	
	}
    
    @ApiOperation(value="导出excel模板",httpMethod="GET",notes="导出excel模板")
  	@RequestMapping("/templet")
  	public void templet(HttpServletRequest request, HttpServletResponse response) throws Exception {
      	Map<String, Object> params = getAllParams(request);
      	List<Role> list = serviceI.selectByMap(params);
      	ExportExcel.exportTempletExcel("角色管理模板.xls", Title, response);

  	}
    
    @ApiOperation(value="excel表数据批量导入",httpMethod="POST",notes="excel表数据批量导入")
  	@RequestMapping("/importExcel")
  	public JSONMessage importExcel(@RequestParam(value = "uploadfile") MultipartFile casesFile) throws Exception {
    	JSONMessage message = null;
  	List<Role> list= null;
  	if(casesFile!=null && !casesFile.isEmpty()) {
  		try {
  			list = ImportExcelJXL.importExcelData(casesFile,Role.class,dataTitlle,Title);
      		boolean ret = serviceI.insertBatch(list);
     		 message = JSONMessage.success("success", ret);
		} catch (Exception e) {
			 message = JSONMessage.success("success", "导入失败:请检测表格内容格式是否正确");
		}    		
  	}else {
  		message = JSONMessage.failure("上传文件为空");
  	}
  	
  	return message;}
    
}
