package cron;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CronJob implements Job{

	
	public void execute(JobExecutionContext h) throws JobExecutionException {
		System.out.println("scduler start ");
		CronSHGMasterDAO cronSHGMasterDAO=new CronSHGMasterDAO();
		cronSHGMasterDAO.fatehSHG();
		System.out.println("----end schduler---");
	}

}
