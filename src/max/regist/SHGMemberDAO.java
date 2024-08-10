package max.regist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utility.AUTOGenerateID;
import utility.JDBCUtil;
import view.MemberDTO;
import view.SHGDTO;

public class SHGMemberDAO {
	public boolean getSHGMemberDetails(String shortname, SHGBean shgBean, SHGMemberBean memberBean) {
		Connection con = JDBCUtil.getConnnn();
		try {

			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("insert into " + shortname + "_shg_master values (?,?,?,?,?,?)");
			int shgcode = AUTOGenerateID.getMemebrID(con, shortname);

			if (shgcode > 0) {
				ps.setInt(1, shgcode);
				ps.setString(2, shgBean.getStCode());
				ps.setString(3, shgBean.getDistrictCode());
				ps.setString(4, shgBean.getGname());
				ps.setString(5, shgBean.getIfsc());
				ps.setString(6, shgBean.getDob());

				int i = ps.executeUpdate();
				if (i > 0) {
					ps = con.prepareStatement("insert into " + shortname + "_shg_member values (?,?,?,?,?)");
					String[] cn = memberBean.getCname();
					String[] a = memberBean.getAge();
					String[] ad = memberBean.getCadhar();

					int d = shgcode;
					for (int j = 0; j < ad.length; j++) {
						ps.setInt(1, ++d);
						ps.setString(2, cn[j]);
						ps.setString(3, a[j]);
						ps.setString(4, ad[j]);
						ps.setInt(5, shgcode);
						ps.addBatch();
					}

					int[] k = ps.executeBatch();
					if (k.length > 0) {
						con.commit();
						return true;
					}

				} else {
					con.rollback();
				}

			}

		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return false;
	}

	public void getSHGDeatils(int shgCode) {

	}

	public Map<String, List<Object>> getSHGDeatils(String shortName) {
		List<Object> shglist = new ArrayList<Object>();
		List<Object> memlist = new ArrayList<Object>();
		Map<String, List<Object>> m = new HashMap<String, List<Object>>();

		try {
			Connection con = JDBCUtil.getConnnn();
			PreparedStatement ps = con.prepareStatement("select * from " + shortName + "_shg_master_temp");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SHGDTO sHGDTO = new SHGDTO();
				sHGDTO.setStateName(rs.getString("state_name"));
				sHGDTO.setDistName(rs.getString("district_name"));
				sHGDTO.setGroupName(rs.getString("group_name"));
				sHGDTO.setIfsc(rs.getString("ifsc"));
				sHGDTO.setShgcode(rs.getInt("shg_code"));
				sHGDTO.setDob(rs.getString("dob"));
				shglist.add(sHGDTO);

			}

			if (shglist.size() > 0) {
				ps = con.prepareStatement("select * from " + shortName + "_shg_member");
				rs = ps.executeQuery();
				while (rs.next()) {
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setMemberCode(rs.getInt("member_code"));
					memberDTO.setName(rs.getString("name"));
					memberDTO.setAdhar(rs.getString("adhar"));
					memberDTO.setAge(rs.getString("age"));
					memberDTO.setShgCode(rs.getInt("shg_code"));
					memlist.add(memberDTO);
				}
			}

			m.put("shgDetail", shglist);
			m.put("memDetail", memlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;

	}

}
