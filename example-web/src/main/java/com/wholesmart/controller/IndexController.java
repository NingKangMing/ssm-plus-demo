package com.wholesmart.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiOperation;

/** 
 * 
 * @author kangming.ning [ningkangming@126.com] 
 * 
 */
@Controller
@RequestMapping("/account")
public class IndexController extends BaseController{


	//private static final Logger logger = Logger.getLogger(IndexController.class);

	@ApiOperation(value="进入主页",httpMethod="GET", response =ModelAndView.class,notes="进入主页")
	@RequestMapping(value ="/index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
        /* logger.debug("test debug.");
         logger.info("test info.");
         logger.warn("test info.");
         logger.error("error");*/
		return new ModelAndView("index");
	}

	@ApiOperation(value="进入主页",httpMethod="GET", response =ModelAndView.class,notes="进入主页")
	@RequestMapping("/")
	public String defaultPage() {

		return "redirect:/account/index";
	}
	
	@ApiOperation(value="测试接口说明",httpMethod="GET", response =ModelAndView.class,notes="接口发布说明")
	@RequestMapping("/test")
	public String test(ModelMap map,@RequestParam(value="testPara3")String testPara) {
        map.addAttribute("test1", new Date());
		return "index";
	}
	
	@ApiOperation(value="测试接口说明",httpMethod="GET", response =ModelAndView.class,notes="接口发布说明")
	@ResponseBody
	@RequestMapping("/test2")
	public String test2(@RequestParam(value="test2Para")String test2Para) {
       
		return JSON.toJSONString(test2Para);
	}

}