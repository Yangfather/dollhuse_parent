package com.dollhouse.admin.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dollhouse.core.service.BaseService;
import com.dollhouse.service.comm.CommonService;

@Component
public class ChartStatisticSchedule  extends BaseSchedule{
	@Autowired
	protected BaseService baseService;
	@Autowired
	protected CommonService commonService;
	@Scheduled(cron="0 0 5 * * ?")
	public void chartStatistic(){
		
	}
}
