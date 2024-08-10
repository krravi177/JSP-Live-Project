package max.regist;

import javax.servlet.http.HttpServletRequest;

public class SHGBean {
  String gname;
  String dob;
  String ifsc;
  String stCode;
  String districtCode;
public String getGname() {
	return gname;
}
public void setGname(String gname) {
	this.gname = gname;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getIfsc() {
	return ifsc;
}
public void setIfsc(String ifsc) {
	this.ifsc = ifsc;
}
public String getStCode() {
	return stCode;
}
public void setStCode(String stCode) {
	this.stCode = stCode;
}
public String getDistrictCode() {
	return districtCode;
}
public void setDistrictCode(String districtCode) {
	this.districtCode = districtCode;
}
  public SHGBean setSHGDetail(HttpServletRequest request)
  {
	  SHGBean sb = new SHGBean();
	  sb.setDistrictCode(request.getParameter("districtCode"));
	  sb.setStCode(request.getParameter("stCode"));
	  sb.setGname(request.getParameter("gname"));
	  sb.setIfsc(request.getParameter("ifsc"));
	  sb.setDob(request.getParameter("dob"));
	return sb;
  }
  
}
