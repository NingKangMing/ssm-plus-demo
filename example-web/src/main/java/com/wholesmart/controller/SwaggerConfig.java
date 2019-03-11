package com.wholesmart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** 
 * 
 * @author kangming.ning [ningkangming@126.com] 
 * 
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.wholesmart.controller"})  //需要扫描的包路径
public class SwaggerConfig extends WebMvcConfigurationSupport{

	/**
	 * 通过 createRestApi函数来构建一个DocketBean
	 * 函数名,可以随意命名
	 */
	@Bean
	public Docket createRestApi() {
		ParameterBuilder parameterBuilder = new ParameterBuilder();
		parameterBuilder
		.parameterType("header") //参数类型支持header, cookie, body, query etc
		.name("token") //参数名
		.defaultValue("") //默认值
		.description("header中token")
		.modelRef(new ModelRef("string"))//指定参数值的类型
		.required(false).build(); //非必需，这里是全局配置
		List<Parameter> params = new ArrayList<Parameter>();
		params.add(parameterBuilder.build());

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())//调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
				.select()
				//控制暴露出去的路径下的实例
				//如果某个接口不想暴露,可以使用以下注解
				//@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
				.apis(RequestHandlerSelectors.basePackage("com.wholesmart.controller"))
				.paths(PathSelectors.any())
				.build().globalOperationParameters(params);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("危险道路智能预警系统接口管理")
				//创建人
				.contact(new Contact("kangming.ning", "", "ningkm@whole-smart.com"))
				//版本号
				.version("1.0")
				//描述
				.description("危险道路智能预警系统接口")
				.build();
	}


}

