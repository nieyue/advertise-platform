package com.yayao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayao.bean.ScheduleJob;
public interface ScheduleJobDao extends BaseDao<ScheduleJob> {
	/** 新增定时任务*/	
	 boolean addScheduleJob(ScheduleJob scheduleJob) ;
	/** 删除定时任务 */	
	 boolean delScheduleJob(Long scheduleJobId) ;
	/** 更新定时任务*/	
	 boolean updateScheduleJob(ScheduleJob scheduleJob);
	/** 装载定时任务 */	
	 ScheduleJob loadScheduleJob(Long scheduleJobId);
	/** 定时任务总共数目 */	
	 int countAll(
            @Param("jobId") Long jobId,
            @Param("type") Integer type
    );
	/** 分页定时任务信息 */
	 List<ScheduleJob> browsePagingScheduleJob(
            @Param("jobId") Long jobId,
            @Param("type") Integer type,
            @Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("orderName") String orderName, @Param("orderWay") String orderWay) ;

}
