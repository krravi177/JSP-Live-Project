package cron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import utility.JDBCUtil;
import view.SHGDTO;

public class CronSHGMasterDAO {
	
	public void fatehSHG()
	{    
		List<SHGDTO> list=new ArrayList<SHGDTO>();
		
			try {
				Connection con = JDBCUtil.getConnnn();
				PreparedStatement ps = con.prepareStatement("select shg.shg_code, mst.state_name,dist.district_name,shg.group_name,"
						+ " shg.ifsc,shg.dob from up_shg_master as shg inner join mst_state as mst"
						+ " on shg.state_code=mst.state_code inner join mst_district as dist on "
						+ "shg.dist_code=dist.district_code");
				ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 SHGDTO sHGDTO=new SHGDTO();
					 sHGDTO.setStateName(rs.getString("state_name"));
					 sHGDTO.setDistName(rs.getString("district_name"));
					 sHGDTO.setGroupName(rs.getString("group_name"));
					 sHGDTO.setIfsc(rs.getString("ifsc"));
					 sHGDTO.setShgcode(rs.getInt("shg_code"));
					 sHGDTO.setDob(rs.getString("dob"));
					 list.add(sHGDTO);
					 
					 
				 }
				 System.out.println("list "+list.size());
				 insertSHGDeatils(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void insertSHGDeatils(List<SHGDTO> list)
	{  Connection con = JDBCUtil.getConnnn();
		try {
			
			    con.setAutoCommit(false);
			PreparedStatement ps=con.prepareStatement("delete from up_shg_master_temp");
			ps.executeUpdate();
			System.out.println("--------Data deleted--- ");
			 ps = con.prepareStatement("insert into up_shg_master_temp values (?,?,?,?,?,?)");
			 for(SHGDTO shg:list)
			 {
			   ps.setInt(1, shg.getShgcode());
				ps.setString(2, shg.getStateName());
				ps.setString(3, shg.getDistName());
				ps.setString(4, shg.getGroupName());
				ps.setString(5, shg.getIfsc());
				ps.setString(6, shg.getDob());
				//ps.executeUpdate();
				ps.addBatch();
				}
			 
			  ps.executeBatch();
			  System.out.println("insert batch data");
			con.commit(); 
		}
			
		 catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		
	
		}
	}
}
