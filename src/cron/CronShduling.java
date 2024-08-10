package cron;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class CronShduling {
	public CronShduling()
	{
		try {
			 SchedulerFactory sf = new   StdSchedulerFactory();	
			Scheduler scdulr = sf.getScheduler();
			scdulr.start();
			
			JobDetail jDetail = new JobDetail("t1","t2",CronJob.class);
	           //0 */12 * * *    every 12 hour 
	           CronTrigger crnTrigger = new CronTrigger("t3","t2","0/90 * * * * ?");
	           scdulr.scheduleJob(jDetail, crnTrigger);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
