package utility;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cron.CronShduling;
import max.login.LoginDAO;

public class ListnerClass implements HttpSessionListener, ServletContextListener {

	public void sessionCreated(HttpSessionEvent sc) {

	}

	public void sessionDestroyed(HttpSessionEvent sd) {

		HttpSession s = sd.getSession();
		String ud = (String) s.getAttribute("uid");
		LoginDAO loginDAO = new LoginDAO();
		loginDAO.logout(ud);
		System.out.println("session logout " + ud);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		CronShduling cronShduling = new CronShduling();

	}

}
