package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AUTOGenerateID {
	
	public static int getMemebrID(Connection con,String stShortName)
	{
		try {
			PreparedStatement ps = con.prepareStatement("select max(member_code) as member_code from "+stShortName+"_shg_member");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			int member_code= rs.getInt("member_code");
			return 1+member_code;
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

}
