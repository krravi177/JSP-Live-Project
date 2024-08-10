package max.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utility.JDBCUtil;
import max.EncryptionPasswordANDVerification;

public class LoginDAO {

	public boolean logout(String uid) {
		try {

			Connection con = JDBCUtil.getConnnn();
			PreparedStatement ps = con.prepareStatement("update login_mst set status = ? where uid = ?");
			ps.setString(1, "Y");
			ps.setString(2, uid);
			int i = ps.executeUpdate();
			if (i > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String login(LoginBean loginBean) {
		try {
			Connection con = JDBCUtil.getConnnn();

			PreparedStatement ps = con.prepareStatement("select uid, status from login_mst where uid=? and pass =?");
			ps.setString(1, loginBean.getUid());
			ps.setString(2, EncryptionPasswordANDVerification.encryptionPassword(loginBean.getPass()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String uid = rs.getString("uid");
				String status = rs.getString("status");
				if (status.equalsIgnoreCase("Y")) {
					ps = con.prepareStatement("update login_mst set status = ? where uid=?");
					ps.setString(1, "N");
					ps.setString(2, loginBean.getUid());
					int i = ps.executeUpdate();
					if (i > 0)
						return uid;

				} else {
					return "AR";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "invalid";

	}

}
