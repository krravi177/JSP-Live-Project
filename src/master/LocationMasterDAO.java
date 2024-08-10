package master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utility.JDBCUtil;



public class LocationMasterDAO {
	public List<DistBean> getDistListByStateCode(String stCode)
	{       List<DistBean> list=new ArrayList<DistBean>();
		try {
			Connection con = JDBCUtil.getConnnn();
PreparedStatement ps = con.prepareStatement("select district_code,district_name from mst_district where state_code = ?");
     		ps.setString(1, stCode.length()==1?"0"+stCode:stCode);
     	ResultSet rs = ps.executeQuery();
     		while(rs.next())
     		{
     			DistBean db = new DistBean();
     			db.setDistName(rs.getString("district_name"));
     			db.setDistCode(rs.getString("district_code"));
     			list.add(db);
     			
     		}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	public StateBean getStateNameBystateShortName(String shortname)
	{      StateBean sb = new StateBean();
		try {
			//UP-ram11
			Connection con = JDBCUtil.getConnnn();
 PreparedStatement ps = con.prepareStatement("select state_code,state_name from mst_state where state_short_name = ?");
		ps.setString(1, shortname.substring(0, 2).toUpperCase());
      ResultSet rs = ps.executeQuery();		
   while(rs.next())
   {
	   sb.setStName(rs.getString("state_name"));
	   sb.setStCode(rs.getString("state_code"));
   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("st "+sb.getStName() +" "+sb.getStCode());
		return sb;
	}

}
