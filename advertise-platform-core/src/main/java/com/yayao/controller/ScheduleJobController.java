package com.yayao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yayao.bean.ScheduleJob;
import com.yayao.schedule.QuartzEventService;
import com.yayao.service.ScheduleJobService;
import com.yayao.util.ResultUtil;
import com.yayao.util.StateResult;
import com.yayao.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;



/**
 * 工作计划控制类
 * @author yy
 *
 */
@Api(tags={"scheduleJob"},value="工作计划",description="工作计划管理")
@RestController
@RequestMapping(value={"/scheduleJob"})
public class ScheduleJobController extends BaseController<ScheduleJob, Long>{
	
	@Autowired
	QuartzEventService quartzEventService;
	@Autowired
	ScheduleJobService scheduleJobService;
	/**
	 * add
	 * @return
	 * @throws SchedulerException 
	 */
	@ApiOperation(value = "工作计划增加", notes = "工作计划增加")
	@RequestMapping(value={"/add"})
	public StateResult addScheduleJob(
			HttpSession session,
			@ModelAttribute ScheduleJob scheduleJob,
			HttpServletResponse response
			) throws SchedulerException{
		boolean b = scheduleJobService.addScheduleJob(scheduleJob);
		return ResultUtil.getSR(b);
		
	}
	/**
	 * 修改
	 * @return
	 * @throws SchedulerException 
	 */
	@ApiOperation(value = "工作计划修改", notes = "工作计划修改")
	@RequestMapping(value={"/update"})
	public StateResult updateScheduleJobList(
			HttpSession session,
			@ModelAttribute ScheduleJob scheduleJob,
			HttpServletResponse response
			) throws SchedulerException{
		boolean b =scheduleJobService.updateScheduleJob(scheduleJob);
		return ResultUtil.getSR(b);
		
	}
	/**
	 *删除
	 * @return
	 * @throws SchedulerException 
	 */
	@ApiOperation(value = "工作计划删除", notes = "工作计划删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="scheduleJobId",value="工作计划ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value={"/delete"})
	public StateResult delScheduleJob(
			HttpSession session,
			@RequestParam("scheduleJobId") Long scheduleJobId,
			HttpServletResponse response
			) throws SchedulerException{
		boolean b = scheduleJobService.delScheduleJob(scheduleJobId);
		return ResultUtil.getSR(b);
		
	}
	/**
	 *加载
	 * @return
	 * @throws SchedulerException 
	 */
	@ApiOperation(value = "工作计划单个加载", notes = "工作计划单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="scheduleJobId",value="工作计划ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value={"/load"})
	public StateResultList<List<ScheduleJob>> loadScheduleJob(
			HttpSession session,
			@RequestParam("scheduleJobId") Long scheduleJobId,
			HttpServletResponse response
			) throws SchedulerException{
		List<ScheduleJob> l=new ArrayList<ScheduleJob>();
		ScheduleJob sj = scheduleJobService.loadScheduleJob(scheduleJobId);
		l.add(sj);
		return ResultUtil.getSlefSRSuccessList(l);
		
	}
	/**
	 *总数
	 * @return
	 * @throws SchedulerException 
	 */
	@ApiOperation(value = "工作计划数量", notes = "工作计划数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="jobId",value="业务id",dataType="long", paramType = "query"),
		  @ApiImplicitParam(name="type",value="类型，1客服消息，2模板消息",dataType="int", paramType = "query"),
		  })
	@RequestMapping(value={"/count"})
	public StateResultList<List<Integer>> countAll(
			@RequestParam(value="jobId",required=false) Long jobId,
			@RequestParam(value="type",required=false) Integer type,
			HttpSession session,
			HttpServletResponse response
			) throws SchedulerException{
		List<Integer> l=new ArrayList<Integer>();
		int f = scheduleJobService.countAll(jobId,type);
		l.add(f);
		return ResultUtil.getSlefSRSuccessList(l);
		
	}
	/**
	 * list
	 * @return
	 * @throws SchedulerException 
	 */
	@ApiOperation(value = "工作计划列表", notes = "工作计划分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="jobId",value="业务id",dataType="long", paramType = "query"),
		  @ApiImplicitParam(name="type",value="类型，1客服消息，2模板消息",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="updateDate"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value={"/list"})
	public StateResultList<List<ScheduleJob>> browseScheduleJobList(
			@RequestParam(value="jobId",required=false)Long jobId,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="schedule_job_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="asc") String orderWay
			){
		List<ScheduleJob> l=new ArrayList<ScheduleJob>();
		 l = scheduleJobService.browsePagingScheduleJob(jobId,type,pageNum, pageSize, orderName, orderWay);
		return  ResultUtil.getSlefSRSuccessList(l); 
		
	}
	/**
	 * list
	 * @return
	 * @throws SchedulerException 
	 */
	@ApiOperation(value = "工作计划列表（quartz内存中）", notes = "工作计划分页浏览（quartz内存中）")
	@RequestMapping(value={"/listquartz"})
	public StateResultList<List<ScheduleJob>> list(
			){
		List<ScheduleJob> l=new ArrayList<ScheduleJob>();
		l = quartzEventService.list();
		return  ResultUtil.getSlefSRSuccessList(l);
		
	}
	/**
	 *暂停
	 * @return
	 * @param scheduleJobId 不存在则全部转状态
	 * @throws SchedulerException 
	 */
	@ApiOperation(value = "工作计划列表", notes = "工作计划分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="scheduleJobId",value="工作计划ID",dataType="long", paramType = "query"),
	  @ApiImplicitParam(name="jobStatus",value="状态",dataType="string", paramType = "query"),
	  })
	@RequestMapping(value={"/turn"})
	public StateResult turnScheduleJob(
			HttpSession session,
			@RequestParam(value="scheduleJobId",required=false) Long scheduleJobId,
			@RequestParam("jobStatus") String jobStatus,
			HttpServletResponse response
			) throws SchedulerException{
		if(scheduleJobId==null||scheduleJobId.equals("")){
			List<ScheduleJob> l = scheduleJobService.browsePagingScheduleJob(null,null,1, Integer.MAX_VALUE, "schedule_job_id", "asc");
			l.forEach((sj)->{
				sj.setJobStatus(jobStatus);
				scheduleJobService.updateScheduleJob(sj);	
			});
			return ResultUtil.getSuccess();
		}
		ScheduleJob sj = scheduleJobService.loadScheduleJob(scheduleJobId);
		sj.setJobStatus(jobStatus);
		scheduleJobService.updateScheduleJob(sj);
		return ResultUtil.getSuccess();
		
	}

}
