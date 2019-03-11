package com.wholesmart.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.feilong.core.Validator;
import com.wholesmart.commom.tree.TreeUtil;
import com.wholesmart.common.web.JSONMessage;
import com.wholesmart.entity.Resource;
import com.wholesmart.entity.User;
import com.wholesmart.service.api.sys.IResourceService;

import io.swagger.annotations.ApiOperation;

/** 
 * 
 * @author kangming.ning [ningkangming@126.com] 
 * 
 */
@Controller
public class IndexController extends BaseController{


	@Autowired
	private IResourceService resourceService;

	@ApiOperation(value="主页",httpMethod="GET",notes="主页")
	@RequestMapping({"/","/index" })
	public ModelAndView index(ModelAndView modelAndView,Map<String, Object> map) {
		modelAndView.setViewName("index");
		User userEntity = (User)SecurityUtils.getSubject().getPrincipal();
		List<Resource> treeMenuList = null;
		if(null!=userEntity&&null!=userEntity.getRole()&&userEntity.getRole().getRoleKey().equals("administrator")){
			treeMenuList = new TreeUtil().treeMenuList(resourceService.selectByMap(null),Long.valueOf(0));
		}else{
			if(null!= userEntity)
			treeMenuList = resourceService.findResourcesMenuByUserId(userEntity.getId());
		}
		map.put("menus", treeMenuList);
		map.put("user", userEntity);
		JSONMessage message = JSONMessage.success("success", map);
		modelAndView.addObject("user", message);
		return modelAndView;
	}
	
	@ApiOperation(value="主页接口",httpMethod="GET",notes="主页接口")
	@ResponseBody
	@RequestMapping(value = "account/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		return modelAndView;
	}


	@ApiOperation(value="登录",httpMethod="GET",notes="登录")
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(HttpServletResponse response,ServletRequest request,@RequestHeader HttpHeaders header,Map<String, Object> map,String msg){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			return "redirect:/index";
		}
		if(Validator.isNotNullOrEmpty(header.get("X-Requested-With"))){
			response.setHeader("sessionstatus", "timeout");
		}
		return "index";
	}

	@ApiOperation(value="登录验证接口",httpMethod="POST",notes="登录验证接口")
	@ResponseBody
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public JSONMessage doLogin(String username,String password,String rememberMe) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>{},{},{}",username,password,rememberMe);
		String msg = "";
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(rememberMe==null?false:true);
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("account", username);
		try{
			subject.login(token);
			logger.info("{}登陆成功!",username);
			subject.getSession().removeAttribute("msg");
			return JSONMessage.success("验证成功");
		}catch(UnknownAccountException e){
			msg = "账户不存在！";
		}catch(IncorrectCredentialsException e){
			msg = "密码错误！";
		}catch (LockedAccountException e) {
			msg = "您的账户已被锁定,请与管理员联系！";
		}catch(ExcessiveAttemptsException e){
			msg = "登录失败次数过多,请稍后再试！";
		}catch(Exception e){
			msg="系统发生错误，请联系管理员！";
		}
		// 此方法不处理登录成功,由shiro进行处理.
		logger.info("{}登陆失败，error={}!",username,msg);
		subject.getSession().setAttribute("msg", msg);
		return JSONMessage.failure(msg);
	}

	@ApiOperation(value="登录注销接口",httpMethod="GET",notes="登录注销接口")
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		// 注销登录
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
	
	@ApiOperation(value="登录注销接口",httpMethod="GET",notes="登录注销接口")
	@ResponseBody
	@RequestMapping(value = "dologout", method = RequestMethod.GET)
	public JSONMessage dologout() {
		// 注销登录
		SecurityUtils.getSubject().logout();
		return JSONMessage.success();
	}


}