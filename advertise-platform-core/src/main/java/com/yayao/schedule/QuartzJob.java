package com.yayao.schedule;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class QuartzJob implements Job{
	@Autowired
	RedisLock redisLock;


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
			try {
				JobDataMap jdm = arg0.getJobDetail().getJobDataMap();
				int type=(int) jdm.get("type");
				Long jobId=(Long) jdm.get("jobId");
				//分布式锁
				if(redisLock.checkStatus(type,jobId)){
					System.out.println("jobId"+jdm.get("jobId")+new Date().toLocaleString());
					if(type==1){
						//客服消息

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
	}


}
