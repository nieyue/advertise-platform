package com.yayao;

import com.yayao.bean.ScheduleJob;
import com.yayao.schedule.QuartzEventService;
import com.yayao.service.PermissionService;
import com.yayao.service.ScheduleJobService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
//@EnableRedisHttpSession
//@Import({DynamicDataSourceRegister.class})
@ServletComponentScan
@EnableSwagger2
@MapperScan("com.yayao.dao")
public class AdvertisePlatformCoreApplication implements ApplicationListener<ApplicationReadyEvent> {

	public static void main(String[] args) {
		SpringApplication.run(AdvertisePlatformCoreApplication.class, args);
	}
	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(
						new ApiInfoBuilder()
								.title("雅耀广告平台接口文档")
								.description("雅耀广告平台接口文档1.0版本")
								.version("1.0")
								.build())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.yayao.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {

		return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
			@Override
			public void customize(ConfigurableServletWebServerFactory   container) {
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/home/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/home/404.html");
				ErrorPage error406Page = new ErrorPage(HttpStatus.NOT_ACCEPTABLE, "/home/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/home/404.html");

				container.addErrorPages( error401Page);
				container.addErrorPages( error404Page);
				container.addErrorPages( error406Page);
				container.addErrorPages( error500Page);
			}
		};
	}

	@Autowired
	PermissionService permissionService;
	@Autowired
	ScheduleJobService scheduleJobService;
	@Autowired
	QuartzEventService quartzEventService;
	/**
	 * 容器初始化
	 * @param event
	 */
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		//初始化权限列表
		permissionService.initPermission();
		//初始化任务计划
		List<ScheduleJob> l = scheduleJobService.browsePagingScheduleJob(null,null,1,Integer.MAX_VALUE,"schedule_job_id","asc");
		l.forEach(sj -> {
			quartzEventService.addScheduleJob(sj);
		});
	}
}
