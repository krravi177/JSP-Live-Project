package max.regist;

import javax.servlet.http.HttpServletRequest;

public class SHGMemberBean {
     String[] cname;
     String[] age;
     String[] cadhar;
	public String[] getCname() {
		return cname;
	}
	public void setCname(String[] cname) {
		this.cname = cname;
	}
	public String[] getAge() {
		return age;
	}
	public void setAge(String[] age) {
		this.age = age;
	}
	public String[] getCadhar() {
		return cadhar;
	}
	public void setCadhar(String[] cadhar) {
		this.cadhar = cadhar;
	}
     
	public SHGMemberBean setMemberBean(HttpServletRequest request)
	{
		SHGMemberBean sHGMemberBean=new SHGMemberBean();
		sHGMemberBean.setCname(request.getParameterValues("cname"));
		sHGMemberBean.setAge(request.getParameterValues("age"));
		sHGMemberBean.setCadhar(request.getParameterValues("cadhar"));
		
		return sHGMemberBean;
	}
}
